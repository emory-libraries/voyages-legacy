var GridEditorGlobals = 
{
	
	gridEditors: new Array(),
	
	registerEditor: function(gridEditor)
	{
		GridEditorGlobals.gridEditors[gridEditor.gridEditorId] = gridEditor;
	},
	
	getEditorField: function(gridEditorId, rowName, columnName)
	{
		var gridEditor = GridEditorGlobals.gridEditors[gridEditorId];
		if (!gridEditor) return null;
		return gridEditor.getField(rowName, columnName);
	},

	listItemSelected: function(gridEditorId, rowName, columnName, depth)
	{
		var gridEditor = GridEditorGlobals.gridEditors[gridEditorId];
		if (gridEditor) gridEditor.listItemSelected(rowName, columnName, depth);
	},
	
	toggleRowGroup: function(gridEditorId, rowGroupIndex)
	{
		var gridEditor = GridEditorGlobals.gridEditors[gridEditorId];
		if (gridEditor) gridEditor.toggleRowGroup(rowGroupIndex);
	},
	
	editNote: function(gridEditorId, columnName, rowName)
	{
		var gridEditor = GridEditorGlobals.gridEditors[gridEditorId];
		if (gridEditor) gridEditor.editNote(columnName, rowName);
	},

	saveNote: function(gridEditorId, columnName, rowName)
	{
		var gridEditor = GridEditorGlobals.gridEditors[gridEditorId];
		if (gridEditor) gridEditor.saveNote(columnName, rowName);
	},
	
	copy: function(gridEditorId, srcColumnName, dstColumnName, rowName)
	{
		var gridEditor = GridEditorGlobals.gridEditors[gridEditorId];
		if (gridEditor) gridEditor.copy(srcColumnName, dstColumnName, rowName);
	}

}

/********************************************
* constructors
*********************************************/

function GridEditor(gridEditorId, formName, mainTableId, expandedGroupsFieldName, fieldTypes, fields, rowGroups)
{
	this.gridEditorId = gridEditorId;
	this.mainTableId = mainTableId;
	this.formName = formName;
	this.fieldTypes = fieldTypes;
	this.fields = fields;
	this.rowGroups = rowGroups;
	this.expandedGroupsFieldName = expandedGroupsFieldName;
}

function GridEditorListFieldType(list)
{
	this.list = list;
}

function GridEditorListItem(value, text, subItems)
{
	this.value = value;
	this.text = text;
	this.subItems = subItems;
}

function GridEditorTextbox(inputName)
{
	this.inputName = inputName;
}

function GridEditorTextarea(inputName)
{
	this.inputName = inputName;
}

function GridEditorDate(yearInputName, monthInputName, dayInputName)
{
	this.yearInputName = yearInputName;
	this.monthInputName = monthInputName;
	this.dayInputName = dayInputName;
}

function GridEditorList(fieldTypeName, selectsNamePrefix, depthFieldName)
{
	this.fieldTypeName = fieldTypeName;
	this.selectsNamePrefix = selectsNamePrefix;
	this.depthFieldName = depthFieldName;
}

function GridEditorRowGroup(firstRowIndex, lastRowIndex, expanded)
{
	this.firstRowIndex = firstRowIndex;
	this.lastRowIndex = lastRowIndex;
	this.expanded = expanded;
}

/********************************************
* GridEditor object
*********************************************/

GridEditor.prototype.getNoteExpandedStatusFieldName = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note_status";
}

GridEditor.prototype.getNoteFieldName = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note";
}

GridEditor.prototype.getNoteEditBoxId = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note_edit";
}

GridEditor.prototype.getNoteReadBoxId = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note_read";
}

GridEditor.prototype.getNoteTextDisplayId = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note_text";
}

GridEditor.prototype.getNoteAddButtonId = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note_add_button";
}

GridEditor.prototype.getNoteEditButtonId = function(column, row)
{
	return this.gridEditorId + "_" + column + "_" + row + "_note_edit_button";
}

GridEditor.prototype.getField = function(rowName, columnName)
{
	var row = this.fields[rowName];
	if (!row) return null;
	return row[columnName];
}

GridEditor.prototype.listItemSelected = function(rowName, columnName, depth)
{
	var field = this.getField(rowName, columnName);
	if (field) field.itemSelected(this, depth);
}

GridEditor.prototype.toggleRowGroup = function(rowGroupIndex)
{

	var rowGroup = this.rowGroups[rowGroupIndex];
	rowGroup.expanded = !rowGroup.expanded;

	var mainTable = document.getElementById(this.mainTableId);	
	for (var i = rowGroup.firstRowIndex; i <= rowGroup.lastRowIndex; i++)
		mainTable.rows[i].style.display = rowGroup.expanded ? "" : "none";

	var expandedRowsIndexes = new Array();
	for (var i = 0; i < this.rowGroups.length; i++)
	{
		if (this.rowGroups[i].expanded)
		{
			expandedRowsIndexes.push(i);		
		}
	}
	
	document.forms[this.formName].elements[this.expandedGroupsFieldName].value =
		expandedRowsIndexes.join(",");
	
}

GridEditor.prototype.changeNoteStatus = function(columnName, rowName, expanded)
{

	var statusFieldName = this.getNoteExpandedStatusFieldName(columnName, rowName);
	var noteFieldName = this.getNoteFieldName(columnName, rowName);
	var editBoxId = this.getNoteEditBoxId(columnName, rowName);
	var readBoxId = this.getNoteReadBoxId(columnName, rowName);
	var textDisplayId = this.getNoteTextDisplayId(columnName, rowName);
	var addButtonId = this.getNoteAddButtonId(columnName, rowName);
	var editButtonId = this.getNoteEditButtonId(columnName, rowName);
	
	var frm = document.forms[this.formName];

	var readBox = document.getElementById(readBoxId);
	var editBox = document.getElementById(editBoxId);
	
	if (expanded)
	{
	
		readBox.style.display = "none";
		editBox.style.display = "block";

		frm.elements[statusFieldName].value = "expanded";
	
	}
	else
	{

		readBox.style.display = "block";
		editBox.style.display = "none";

		frm.elements[statusFieldName].value = "collapsed";

		var note = frm.elements[noteFieldName].value;
		var isEmpty = note.match(/^\s*$/);
		
		var textDisplay = document.getElementById(textDisplayId);
		var addButton = document.getElementById(addButtonId);
		var editButton = document.getElementById(editButtonId);

		if (isEmpty)
		{
			textDisplay.innerHTML = "";
			textDisplay.style.display = "none";
			addButton.style.display = "";
			editButton.style.display = "none";
		}
		else
		{
			textDisplay.innerHTML = note;
			textDisplay.style.display = "";
			addButton.style.display = "none";
			editButton.style.display = "";
		}

	}

}

GridEditor.prototype.editNote = function(columnName, rowName)
{
	this.changeNoteStatus(columnName, rowName, true);
}

GridEditor.prototype.saveNote = function(columnName, rowName)
{
	this.changeNoteStatus(columnName, rowName, false);
}

GridEditor.prototype.copy = function(srcColumnName, dstColumnName, rowName)
{

	var row = this.fields[rowName];
	if (!row) return;

	var srcField = row[srcColumnName];
	var dstField = row[dstColumnName];
	if (!srcField || !dstField) return;
	
	var value = srcField.getValue(this);
	dstField.setValue(this, value);

}

/********************************************
* GridEditorTextbox object
*********************************************/

GridEditorTextbox.prototype.getValue = function(grid)
{
	return document.forms[grid.formName].elements[this.inputName].value;
}

GridEditorTextbox.prototype.setValue = function(grid, value)
{
	document.forms[grid.formName].elements[this.inputName].value = value;
}

/********************************************
* GridEditorTextarea object
*********************************************/

GridEditorTextarea.prototype.getValue = GridEditorTextbox.prototype.getValue;
GridEditorTextarea.prototype.setValue = GridEditorTextbox.prototype.setValue;

/********************************************
* GridEditorDate object
*********************************************/

GridEditorDate.prototype.getValue = function(grid)
{
	var frm = document.forms[grid.formName];
	return {
		year: frm.elements[this.yearInputName].value,
		month: frm.elements[this.monthInputName].value,
		day: frm.elements[this.dayInputName].value};
}

GridEditorDate.prototype.setValue = function(grid, value)
{
	var frm = document.forms[grid.formName];
	frm.elements[this.yearInputName].value = value.year;
	frm.elements[this.monthInputName].value = value.month;
	frm.elements[this.dayInputName].value = value.day;
}

/********************************************
* GridEditorList object
*********************************************/

GridEditorList.prototype.getValue = function(grid)
{
	
	var frm = document.forms[grid.formName];
	var depth = parseInt(frm.elements[this.depthFieldName].value);

	var values = new Array();	
	for (var i = 0; i < depth; i++)
	{
		var sel = frm.elements[this.selectsNamePrefix + i];
		values[i] = sel.value;
	}
	
	return values;

}

GridEditorList.prototype.setValue = function(grid, values)
{

	var frm = document.forms[grid.formName];
	var nextItems = grid.fieldTypes[this.fieldTypeName].list;
	
	var i = 0;
	while (nextItems && nextItems.length != 0)
	{
	
		var items = nextItems;

		var sel = frm.elements[this.selectsNamePrefix + i];;
		sel.style.display = "";
		
		ElementUtils.removeAllOptions(sel);

		for (var k = 0; k < items.length; k++)
			ElementUtils.addOption(sel,
				items[k].value,
				items[k].text);
				
		nextItems = null;

		for (var j = 0; j < items.length; j++)
		{
			if (items[j].value == values[i])
			{
				nextItems = items[j].subItems;
				sel.selectedIndex = j;
			}
		}
		
		i++;

	}
	
	frm.elements[this.depthFieldName].value = i;
	
	var sel = frm.elements[this.selectsNamePrefix + i];
	while (sel)
	{
		sel.style.display = "none";
		sel = frm.elements[this.selectsNamePrefix + (i++)];
	}

}

GridEditorList.prototype.itemSelected = function(grid, depth)
{

	var form = document.forms[grid.formName];
	var i = 0;
	
	var items = grid.fieldTypes[this.fieldTypeName].list;
	while (i <= depth)
	{
		items = items[form.elements[this.selectsNamePrefix + i].selectedIndex].subItems;
		i++;
	}
	
	var sel = form.elements[this.selectsNamePrefix + i];

	while (items.length > 0 && sel)
	{
		
		sel.style.display = "";
		
		ElementUtils.removeAllOptions(sel);
		
		for (var j = 0; j < items.length; j++)
			ElementUtils.addOption(sel,
				items[j].value,
				items[j].text);
		
		i++;
		sel = form.elements[this.selectsNamePrefix + i];
		
		if (items[0].subItems.length > 0)
		{
			items = items[0].subItems;	
		}
		else
		{
			break;
		}

	}
	
	form.elements[this.depthFieldName].value = i;

	while (sel)
	{
		sel.style.display = "none";
		sel = form.elements[this.selectsNamePrefix + (++i)];
	}

}
/*

	NUMERIC:
	
	typeFieldName,
	fromId,
	dashId,
	toId,
	leId,
	geId,
	eqId

	DATE:
	
	typeFieldName,
	tdFromMonthId,
	tdSlashBetweenStartId,
	tdFromYearId,
	tdDashId,
	tdToMonthId,
	tdSlashBetweenEndId,
	tdToYearId,
	tdLeMonthId,
	tdSlashLeId,
	tdLeYearId,
	tdGeMonthId,
	tdSlashGeId,
	tdGeYearId,
	tdEqMonthId,
	tdSlashEqId,
	tdEqYearId,
	monthFieldName,
	monthTdId,
	monthsTds
	
	LIST:
	

*/

var QueryBuilderGlobals = 
{

	builders: new Array(),
	
	registerBuilder: function(builder)
	{
		this.builders[builder.builderId] = builder;
	},

	updateTotal: function(builderId, delay)
	{
		var builder = this.builders[builderId];
		if (builder) builder.updateTotal(delay);
	},
	
	moveConditionUp: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.moveConditionUp(attributeId);
	},
	
	moveConditionDown: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.moveConditionDown(attributeId);
	},
	
	deleteCondition: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.deleteCondition(attributeId);
	},
	
	changeNumericRangeType: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.changeNumericRangeType(attributeId);
	},
	
	changeDateRangeType: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.changeDateRangeType(attributeId);
	},
	
	toggleMonth: function(builderId, attributeId, month)
	{
		var builder = this.builders[builderId];
		if (builder) builder.toggleMonth(attributeId, month);
	},
	
	openList: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.openList(attributeId);
	},
	
	closeList: function(builderId, attributeId)
	{
		var builder = this.builders[builderId];
		if (builder) builder.closeList(attributeId);
	}

}

function QueryBuilder(builderId, formName, attributesFieldName, updateTotalFieldName, conditions)
{
	this.formName = formName;
	this.builderId = builderId;
	this.updateTotalFieldName = updateTotalFieldName;
	this.conditions = conditions;
	this.attributesFieldName = attributesFieldName;
	this.timeoutId = "";
}

QueryBuilder.prototype.updateTotal = function(delay)
{

	if (!ajaxAnywhere) return;
	
	// set to true when submit
	// so that the component can fire an event
	var flagField = document.forms[this.formName].elements[this.updateTotalFieldName];
	
	// delay
	if (delay > 0)
		this.timeoutId = Timer.extendCall(
			this.timeoutId,
			this, "doUpdateExpectedTotal",
			delay);
	
	// immediate
	else
		this.doUpdateExpectedTotal();
		
}

QueryBuilder.prototype.doUpdateExpectedTotal = function()
{
	document.forms[this.formName].elements[this.updateTotalFieldName].value = "true";
	ajaxAnywhere.submitAJAX(null, null);
}

QueryBuilder.prototype.moveConditionUp = function(attributeId)
{
	var cond = document.getElementById(this.conditions[attributeId].conditionDivId);
	var attrListField = document.forms[this.formName].elements[this.attributesFieldName];
	var attrs = attrListField.value.split(',');
	for (var i=0; i<attrs.length; i++)
	{
		if (attrs[i] == attributeId)
		{
			if (i != 0)
			{
				var prevCond = cond.previousSibling;
				var parent = cond.parentNode;
				parent.removeChild(cond);
				parent.insertBefore(cond, prevCond);
				attrs[i] = attrs[i-1];
				attrs[i-1] = attributeId;
				attrListField.value = attrs.join(',');
				if (Scriptaculous)
				{
					Element.setOpacity(cond, 0);
					Effect.Appear(cond, {duration: 0.5});
				}
			}
			return;
		}
	}
}

QueryBuilder.prototype.moveConditionDown = function(attributeId)
{
	var cond = document.getElementById(this.conditions[attributeId].conditionDivId);
	var attrListField = document.forms[this.formName].elements[this.attributesFieldName];
	var attrs = attrListField.value.split(',');
	for (var i=0; i<attrs.length; i++)
	{
		if (attrs[i] == attributeId)
		{
			if (i != attrs.length-1)
			{
				var nextNextCond = cond.nextSibling.nextSibling;
				var parent = cond.parentNode;
				parent.removeChild(cond);
				parent.insertBefore(cond, nextNextCond);
				attrs[i] = attrs[i+1];
				attrs[i+1] = attributeId;
				attrListField.value = attrs.join(',');
				if (Scriptaculous)
				{
					Element.setOpacity(cond, 0);
					Effect.Appear(cond, {duration: 0.5});
				}
			}
			return;
		}
	}
}

QueryBuilder.prototype.deleteCondition = function(attributeId)
{

	var cond = document.getElementById(this.conditions[attributeId].conditionDivId);
	var attrListField = document.forms[this.formName].elements[this.attributesFieldName];
	var attrs = attrListField.value.split(',');
	for (var i=0; i<attrs.length; i++) 
	{
		if (attrs[i] == attributeId)
		{
			attrs.splice(i, 1);
			delete this.conditions[attributeId];
			attrListField.value = attrs.join(',');
			if (Scriptaculous)
			{
				new Effect.Opacity(cond,
				{
					from: 1.0, to: 0.0, duration: 0.5,
					afterFinishInternal: function(effect)
					{
						effect.element.parentNode.removeChild(effect.element);
					}
				});
			}
			else
			{
				cond.parentNode.removeChild(cond);
			}
			this.updateTotal(0);
			return;
		}
	}	
}

QueryBuilder.prototype.changeNumericRangeType = function(attributeId)
{
	var cond = this.conditions[attributeId];
	var type = document.forms[this.formName].elements[cond.typeFieldName].selectedIndex;
	document.getElementById(cond.fromId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.dashId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.toId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.leId).style.display = (type == 1) ? '' : 'none';
	document.getElementById(cond.geId).style.display = (type == 2) ? '' : 'none';
	document.getElementById(cond.eqId).style.display = (type == 3) ? '' : 'none';
	this.updateTotal(0);
}

QueryBuilder.prototype.changeDateRangeType = function(attributeId)
{
	var cond = this.conditions[attributeId];
	var type = document.forms[this.formName].elements[cond.typeFieldName].selectedIndex;
	document.getElementById(cond.tdFromMonthId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdSlashBetweenStartId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdFromYearId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdDashId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdToMonthId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdSlashBetweenEndId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdToYearId).style.display = (type == 0) ? '' : 'none';
	document.getElementById(cond.tdLeMonthId).style.display = (type == 1) ? '' : 'none';
	document.getElementById(cond.tdSlashLeId).style.display = (type == 1) ? '' : 'none';
	document.getElementById(cond.tdLeYearId).style.display = (type == 1) ? '' : 'none';
	document.getElementById(cond.tdGeMonthId).style.display = (type == 2) ? '' : 'none';
	document.getElementById(cond.tdSlashGeId).style.display = (type == 2) ? '' : 'none';
	document.getElementById(cond.tdGeYearId).style.display = (type == 2) ? '' : 'none';
	document.getElementById(cond.tdEqMonthId).style.display = (type == 3) ? '' : 'none';
	document.getElementById(cond.tdSlashEqId).style.display = (type == 3) ? '' : 'none';
	document.getElementById(cond.tdEqYearId).style.display = (type == 3) ? '' : 'none';
	this.updateTotal(0);
}

QueryBuilder.prototype.toggleMonth = function(attributeId, month)
{
	var cond = this.conditions[attributeId];
	var monthInput = document.forms[this.formName].elements[this.monthFieldName];
	var monthTd = document.getElementById(cond.monthsTds[month]);
	if (monthInput.value == "true")
	{
		monthInput.value = "false";
		monthTd.className = 'query-builder-range-month-delected';
	}
	else
	{
		monthInput.value = "true";
		monthTd.className = 'query-builder-range-month-selected';
	}
	this.updateTotal(0);
}

QueryBuilder.prototype.openList = function(attributeId)
{
	var cond = this.conditions[attributeId];
	document.getElementById(cond.popupElementId).style.display = "block";
}

QueryBuilder.prototype.closeList = function(attributeId)
{
	var cond = this.conditions[attributeId];
	document.getElementById(cond.popupElementId).style.display = "none";
	
	var names = new Array();
	var allInputs = document.forms[this.formName].getElementsByTagName("input");
	for (var i=0; i<allInputs.length; i++)
	{
		var input = allInputs[i];
		if (input.type == "checkbox" && input.name == cond.itemsField && input.checked)
		{
			names.push(cond.items[input.value]);
			alert(input.value + ": " + cond.items[input.value] + " " + cond.items);
		}
	}
	
	document.getElementById(cond.displayElementId).innerHTML = names.join(", ");
}


/*
QueryBuilder.prototype.showList = function(attributeId, hiddenFieldName, displayFieldName)
{

	var url = "dictionary-list.jsp" +
		"?attributeId=" + encodeURIComponent(attributeId) + 
		"&formName=" + encodeURIComponent(formName) +
		"&updateTotalFieldName=" + encodeURIComponent(updateTotalFieldName) +
		"&hiddenFieldName=" + encodeURIComponent(hiddenFieldName) +
		"&displayFieldName=" + encodeURIComponent(displayFieldName) +
		"&builderId=" + encodeURIComponent(builderId);

	window.open(url, "search-list", "width=300,height=500,resizable=yes,scrollbars=yes,status=no");

}

*/
	
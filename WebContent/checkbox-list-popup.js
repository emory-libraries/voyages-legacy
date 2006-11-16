var CheckboxListPopupGlobals = 
{

	checkboxLists: new Array(),

	registerCheckboxList: function(checkboxList)
	{
		CheckboxListPopupGlobals.checkboxLists[checkboxList.checkboxListId] = checkboxList;
	},
	
	popupShow: function(checkboxListId, popupId, mainItemId, subItemId, imageUrl)
	{
		var checkboxList = CheckboxListPopupGlobals.checkboxLists[checkboxListId];
		if (checkboxList) checkboxList.popupShow(popupId, mainItemId, subItemId, imageUrl);
	},

	popupHide: function(checkboxListId, popupId, mainItemId, subItemId)
	{
		var checkboxList = CheckboxListPopupGlobals.checkboxLists[checkboxListId];
		if (checkboxList) checkboxList.popupHide(popupId, mainItemId, subItemId);
	}

}

function CheckboxListPopup(
	checkboxListId,
	formName)
{
	this.checkboxListId = checkboxListId;
	this.formName = formName;
}

CheckboxListPopup.prototype.popupShow = function(popupId, mainItemId, subItemId, imageUrl)
{
	var popup = document.getElementById(popupId);
	var mainItem = document.getElementById(mainItemId);
	var subItem = document.getElementById(subItemId);
	popup.style.display = "block";
	if (imageUrl) popup.style.backgroundImage = "url(" + imageUrl + ")";
	mainItem.className = "checkbox-list-item-0-active";
	if (subItem) subItem.className = "checkbox-list-item-1-active";
}

CheckboxListPopup.prototype.popupHide = function(popupId, mainItemId, subItemId)
{
	var popup = document.getElementById(popupId);
	var mainItem = document.getElementById(mainItemId);
	var subItem = document.getElementById(subItemId);
	popup.style.display = "none";
	mainItem.className = "checkbox-list-item-0";
	if (subItem) subItem.className = "checkbox-list-item-1";
}

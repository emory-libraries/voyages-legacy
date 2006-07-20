var IE = navigator.userAgent.indexOf("MSIE 7.0") || navigator.userAgent.indexOf("MSIE 6.0") != -1 || navigator.userAgent.indexOf("MSIE 5.5") != -1;
var GK = navigator.userAgent.indexOf("Gecko") != -1;

function get_event(e)
{
	return window.event ? window.event : e;
}

var EventUtils = 
{

	getRelativePosX: function(e)
	{
		return e.x ? e.x : e.layerX;
	}
	
	getRelativePosX: function(e)
	{
		return e.y ? e.y : e.layerY;
	}

}

var ElementUtils =
{
	
	getOffsetLeft: function(el)
	{
		// IE
		if (el.clientLeft)
		{
			return el.clientLeft;
		}
		
		// others
		var curleft = 0;
		while (el.offsetParent)
		{
			curleft += el.offsetLeft
			el = el.offsetParent;
		}
		return curleft;
		
	}
	
	getOffsetLeft: getOffsetTop(el)
	{
	
		// IE
		if (el.clientTop)
		{
			return el.clientTop;
		}
		
		// others
		var curtop = 0;
		while (el.offsetParent)
		{
			curtop += el.offsetTop
			el = el.offsetParent;
		}
		return curtop;
		
	}
	
	deleteAllChildren: function(el)
	{
		while (el.hasChildNodes())
			el.removeChild(el.firstChild);
	}
	
	getPageWidth: function()
	{
		if (self.pageXOffset) // all except IE
		{
			return self.innerWidth;
		}
		else if (document.documentElement && document.documentElement.clientWidth) // IE6 Strict
		{
			return document.documentElement.clientWidth;
		}
		else if (document.body) // all other IE
		{
			return document.body.clientWidth;
		}
	}
	
	getPageHeight: function()
	{
		if (self.pageYOffset) // all except IE
		{
			return self.innerHeight;
		}
		else if (document.documentElement && document.documentElement.clientHeight) // IE6 Strict
		{
			return document.documentElement.clientHeight;
		}
		else if (document.body) // all other IE
		{
			return document.body.clientHeight;
		}
	}
	
	getScrollLeft: function(el)
	{
		var offset = 0;
		if (el.offsetParent != null)	// IE
		{
			var actual = el;
			while(actual)
			{
				if (actual.scrollLeft != null)
					offset += actual.scrollLeft;
				actual=actual.offsetParent;
			}
		}
		else // all other IE
		{
			offset = document.body.scrollLeft;
		}
		return offset;
	}
	
	getScrollTop: function(el)
	{
		var offset = 0;
		if (el.offsetParent != null)	// IE
		{
			var actual = el;
			while(actual)
			{
				if (actual.scrollTop != null)
					offset += actual.scrollTop;
				actual=actual.offsetParent;
			}
		}
		else // all other IE
		{
			offset = document.body.scrollTop;
		}
		return offset;
	}

}

var EventAttacher =
{
	map: new Array(),

	findRegistrationIndex: function(element, eventType)
	{
		for (var i=0; i<Events.map.length; i++)
		{
			var req = Events.map[i];
			if (req.element == element && req.eventType == eventType)
			{
				return i;
			}
		}
		return -1;
	},

	findRegistration: function(element, eventType)
	{
		var i = Events.findRegistrationIndex(element, eventType);
		return i == -1 ? null : Events.map[i];
	},

	attach: function(element, eventType, object, handler)
	{

		if (element.attachEvent)
		{
			element.attachEvent("on" + eventType, Events.globalHandler);
		}
		else if (element.addEventListener)
		{
			element.addEventListener(eventType, Events.globalHandler, false);
		}

		var reg = Events.findRegistration(element, eventType);
		if (reg == null)
		{
			reg = new Object();
			this.map.push(reg);
		}

		if (object == null) object = window;
		reg.element = element;
		reg.object = object;
		reg.eventType = eventType;
		reg.handler = handler;

	},

	attachById: function(elementId, eventType, object, handler)
	{
		var element = document.getElementById(elementId);
		this.attach(element, eventType, object, handler);
	},
	
	detach: function(element, eventType)
	{
		var i = Events.findRegistrationIndex(element, eventType);
		if (i != -1) Events.map.splice(i, 1);
	},
	
	detachById: function(elementId, eventType)
	{
		var element = document.getElementById(elementId);
		Events.detach(element, eventType);
	},

	globalHandler: function(event)
	{
		if (!event) event = window.event;
		var element = event.srcElement ? event.srcElement : this;
		var reg = Events.findRegistration(element, event.type);
		if (reg != null) reg.object[reg.handler](event);
	}

}

var Timer =
{

	map: new Array(),
	nextId: 0,
	
	delayedCall: function(object, method, delay)
	{
	
		var id = Timer.nextId;
		Timer.nextId ++;
	
		if (object == null) object = window;
		var reg = new Object();
		reg.object = object;
		reg.method = method;
		reg.tid = window.setTimeout("Timer.globalHandler(" + id + ")", delay);
		
		Timer.map["call_" + id] = reg;
		
		return id;

	},
	
	cancelCall: function(id)
	{
		var reg = Timer.map["call_" + id];
		if (reg)
		{
			window.clearTimeout(reg.tid);
			delete Timer.map["call_" + id];
		}
	},
	
	globalHandler: function(id)
	{
		var reg = Timer.map["call_" + id];
		if (reg)
		{
			delete Timer.map["call_" + id];
			reg.object[reg.method]();
		}
	}

}

var ObjectUtils = 
{
	printObject: function(obj)
	{
		if (!obj)
		{
			alert(obj);
		}
		else
		{
			var ret = "";
			for (var k in obj) ret += k + " = " + obj[k] + "\n";
			alert(ret);
		}
	}
}
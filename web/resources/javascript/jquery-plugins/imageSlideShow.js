$.fn.imageSlideShow = function(options) {
    
    var opts = $.extend({}, $.fn.imageSlideShow.defaults, options);
    
    var mainElem = $(this);
    var current = 0;
    var found = false;
    var images = mainElem.find('li img');
    var count = 0;
    var lastPosBottom = 1;
    images.each(function() {
	if($(this).parent().hasClass('active')) {
	    found = true;
	}
	if(!found) {
	    current++;
	}
	if(count > 0) {
	    if(lastPosBottom == 1) {
		$(this).parent().find('.description').css('top', '0');
		$(this).parent().find('.description').css('bottom', 'auto');
		lastPosBottom = 0;
	    }
	    else {
		$(this).parent().find('.description').css('bottom', '0');
		$(this).parent().find('.description').css('top', 'auto');
		lastPosBottom = 1;
	    }
	}
	count++;
    });
    var imageElem = $(images[current]);    
    var width = imageElem.width();
    var height = imageElem.height();
    var mainWidth = mainElem.width();
    var mainHeight = mainElem.height();
    var cutWidth;
    var cutHeight;    
    if(height < width) {
	cutWidth = Math.ceil(((height-(height-mainHeight))/height)*width);
	cutHeight = mainHeight;
    }
    else {
	cutHeight = Math.ceil(((width-(width-mainWidth))/width)*height);
	cutWidth = mainWidth;
    }
    var topMode = 0;
    
    startAnimation();
    
    function switchImage() {
	var next = current+1;
	if(next >= count) {
	    next = 0;
	}
	/*imageElem.animate({
	    width:	imageElem.parent().css('width'),
	    height: 	imageElem.parent().css('height')
	}, opts.speed, function() {*/
	    imageElem.parent().fadeOut(opts.fadeSpeed, function() {
		imageElem.css('width', 'auto');
		imageElem.css('height', 'auto');
		imageElem.parent().removeClass('active');
		$(images[next]).parent().fadeIn(opts.fadeSpeed, function() {
		    $(images[next]).parent().addClass('active');
		    current = next;
		    imageElem = $(images[current]);
		    width = imageElem.width();
		    height = imageElem.height();
		    if(height < width) {
			cutWidth = Math.ceil(((height-(height-mainHeight))/height)*width);
			cutHeight = mainHeight;
		    }
		    else {
			cutHeight = Math.ceil(((width-(width-mainWidth))/width)*height);
			cutWidth = mainWidth;
		    }
		    startAnimation();
		});	    
	    });
	//});
    }
    
    
    function startAnimation() {
	imageElem.animate({
	    left: (opts.viewWidth-width),
	    top: (opts.viewHeight-height),
	}, opts.speed, function() {
	    topMode = 0;
	    toLeft();
	});
    }
    
    function toLeft() {
	var animationOpts = {left: 0};
	if(topMode == 1) {
	    animationOpts = {left: 0, top: (opts.viewHeight-cutHeight)};
	}
	imageElem.animate(animationOpts, opts.speed, function() {
	    if(topMode == 1) {
		switchImage();
	    }
	    else {
		toTopRight();
	    }
	});
    }
    
    function toTopRight() {
	imageElem.animate({
	    left: (opts.viewWidth-cutWidth) + 'px',
	    top: '0px',
	    width: (cutWidth) +'px',
	    height: (cutHeight) +'px'
	}, opts.speed, function() {
	    topMode = 1;
	    toLeft();
	});
    } 

};

$.fn.imageSlideShow.defaults = {
    speed:	'fast',
    fadeSpeed:	'fast',
    viewWidth:	450,
    viewHeight: 400
}

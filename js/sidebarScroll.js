// Cache selectors for faster performance.
var $window = $(window)
$document = $(document),
    $filterClass = $('#filterScroll'),
    $filterAnchor = $('#filterAnchor'),
    scrollPos = $window.scrollTop();
// Run this on scroll events.
$window.scroll(function() {
    var window_top = $window.scrollTop();
    var div_top = $filterAnchor.offset().top;
    var diff = scrollPos - window_top;
    var top = parseInt($filterClass.css('top'));
    var windowHeight = $window.height();
    var fixedHeight = $filterClass.height();
    if (isNaN(top)) {
        top = $filterClass.position().top
    }
    if (fixedHeight > $document.height()) {
        $filterClass.removeClass('fixed');
    } else if (diff < 0) {
        $filterClass.addClass('fixed');
        if(windowHeight < fixedHeight) {
            $filterClass.removeClass('stick-top');
            if (!$filterClass.hasClass('stick-bottom')) {
                if(top < 0 && fixedHeight + top < windowHeight) {
                    $filterClass.addClass('stick-bottom');
                    $filterClass.css('top', '');
                } else {
                    $filterClass.css('top', top+diff);
                }
            } 
        } else {
            $filterClass.removeClass('stick-bottom');
            if (!$filterClass.hasClass('stick-top')) {
                if (top < 0) {
                    $filterClass.addClass('stick-top');
                    $filterClass.css('top', 0);
                } else {
                    $filterClass.css('top', top+diff);
                }
            }
        }
        
    } else {
        $filterClass.addClass('fixed');                
        if (windowHeight > fixedHeight) {
            $filterClass.removeClass('stick-top');
            if (!$filterClass.hasClass('stick-bottom')) {
               if (windowHeight <= fixedHeight + top) {
                    $filterClass.addClass('stick-bottom');
                    $filterClass.css('top', '');
                } else {
                    $filterClass.css('top', top+diff);
                }
            }

        } else {
            $filterClass.removeClass('stick-bottom');
            if (top >= 0) {
                if (!$filterClass.hasClass('stick-top')) {
                    $filterClass.addClass('stick-top');
                    $filterClass.css('top', 0);
                }
            } else {
                $filterClass.css('top', top+diff);
            }
        }
    }
    scrollPos = window_top;
});
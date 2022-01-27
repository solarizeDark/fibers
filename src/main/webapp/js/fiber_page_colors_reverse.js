let colorProperties = ['color', 'background-color'];

$("*").get().reverse().each(function() {
    let color = null;

    for (let prop in colorProperties) {

        prop = colorProperties[prop];

        if (!$(this).css(prop)) continue;
        color = new RGBColor($(this).css(prop));
        if (color.ok) {
            $(this).css(prop, 'rgb(' + (255 - color.r) + ', ' + (255 - color.g) + ', ' + (255 - color.b) + ')');
        }
        color = null;
    }
});
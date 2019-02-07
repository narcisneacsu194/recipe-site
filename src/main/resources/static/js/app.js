$("#add-another-step-button").click(function () {
    event.preventDefault();

    var divWithLastStep = $(".step-row").last();

    var id = parseInt(
        divWithLastStep
            .children('div')
            .children('p')
            .children('textarea')
            .attr('id')
            .split('.')[0]
            .split('steps')[1]
    );

    var newId = id + 1;
    var newHiddenInputId = 'steps' + newId + '.id';
    var newHiddenInputName = 'steps[' + newId + '].id';
    var newStepInputId = 'steps' + newId + '.description';
    var newStepInputName = 'steps[' + newId + '].description';

    var newDiv =
        "<div class='step-row'>" +
            "<input type='hidden' id='" + newHiddenInputId + "' name='" +
                newHiddenInputName + "'>" +
            "<div class='prefix-20 grid-60'>" +
                "<p>" +
                     "<textarea id='" + newStepInputId + "' " +
                     "name='" + newStepInputName + "' " +
                     "rows='4' cols='50' placeholder='description...'></textarea>" +
                "</p>" +
            "</div>" +
        "</div>";

    divWithLastStep.after(
        newDiv
    );

});

$("#remove-step-button").click(function(){
    event.preventDefault();

    var lastIngredientRow = $(".step-row").last();
    var len = $(".step-row").length;
    if(len > 1)
        lastIngredientRow.remove();
});

$("#add-another-ingredient-button").click(function () {
        event.preventDefault();

        var divWithLastStep = $(".ingredient-row").last();

        var id = parseInt(
            divWithLastStep
                .children('div')
                .children('p')
                .children('input')
                .attr('id')
                .split('.')[0]
                .split('ingredients')[1]
        );

        var newId = id + 1;
        var newHiddenInputId = 'ingredients' + newId + '.id';
        var newHiddenInputName = 'ingredients[' + newId + '].id';

        var newIngredientDescriptionInputIdField = 'ingredients' + newId + '.description';
        var newIngredientDescriptionInputNameField = 'ingredients[' + newId + '].description';

        var newDiv =
            "<div class='ingredient-row'>" +
                "<input type='hidden' id='" + newHiddenInputId + "' name='" +
                    newHiddenInputName + "'>" +
                "<div class='prefix-20 grid-50'>" +
                    "<p>" +
                        "<input id='" + newIngredientDescriptionInputIdField + "' " +
                        "name='" + newIngredientDescriptionInputNameField + "' " +
                        "placeholder='description...'>" +
                    "</p>" +
                "</div>" +
            "</div>";

        divWithLastStep.after(
            newDiv
        );

});

$("#remove-ingredient-button").click(function(){
    event.preventDefault();

    var lastIngredientRow = $(".ingredient-row").last();
    var len = $(".ingredient-row").length;
    if(len > 1){
        $(".ingredient-row").val('');
        lastIngredientRow.remove();
    }
});
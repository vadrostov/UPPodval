function doDecAjax(e) {
    if(e){
        var val=e.target.getAttribute('data-val');
        var id=e.target.getAttribute('data-id');


        $.ajax({
                url: 'commentrate',
                type: 'get',
                dataType: 'json',
                contentType: 'application/json',
                mimeType: 'application/json',
                data : ({
                    text: id,
                    val: val,

                }),
                success: function (data) {

                    var result=data.rate;
                    $("#commentRate").text(result);
                }
            }

        )

    }
}
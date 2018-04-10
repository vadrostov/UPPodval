

var Comment = function (author, id, content, level, parent, rate) {
    this.author=author;
    this.id=id;
    this.content=content;
    this.level=+level;
    this.parent=+parent;
    this.rate=+rate;

    this.makeComment= function () {

        var authorTooltip= $('<div>').append($('<div>', {'class':'container tooltip tooltip-custom ', style: 'width: 200px; border-style: solid; border-width: 2px'}).append($('<div>', {
            'class': 'row'
        }).append([
            $('<div>', {'class': "col-lg-3 tooltip-inner"}),

            $('<div>', {
                'class': 'col-lg-9'
            }).append([
                $('<div>', {
                    'class': 'row'
                }).append($('<div>'), {'class': "col-lg-12 "}).text(this.author),
                $('<div>', {
                    'class': 'row'
                }).append($('<div>'), {'class': "col-lg-12"}).text('432432')

            ])
        ])));
        var toolstr=$(authorTooltip).html();
        var imgStr='<img src=\'/resources/imgs/1.png\'  style="width: 100%"/>'

        var commentContentDiv=   $("<div>", {"class":"row"}).append($("<div>", {"class":"col-lg-12", id:"col12${comment.id}"}).
        append([
                $("<div>", {"class":"row"}).append([
                    $("<div>", {
                        "class": "col-lg-4",
                        text: this.author


                    }).tooltip({
                        template: toolstr,
                        title: imgStr,
                        html: true,
                        placement: top
                    }),
                    $("<div>", {
                        "class": "col-lg-4"

                    }),
                    $("<div>", {
                        "class": "col-lg-4",
                    }).append([
                        $('<p>', {
                            'data-val': 'dec',
                            'data-id':this.id
                        }).text('-')
                    .bind('click', doDecAjax),
                        $('<p>').text(' '+this.rate+' '),
                        $('<p>', {
                            'data-val': 'inc',
                            'data-id':this.id
                        }).text('+')
                            .bind('click', doDecAjax)
                    ])

                ])
                ,
                $("<div>", {"class":"row"}).append(


                    $("<div>", {
                        "class": "col-lg-12",
                        text: this.content
                    }))
                ,
                $("<div>", {"class":"row"}).append([
                    $("<div>", {"class":"col-lg-8"}),
                    $("<div>", {"class":"col-lg-4"}).append(
                        $("<p>", {id: this.id, text: "Ответить"}).click(showCommentForm).css('cursor', 'pointer')
                    )
                ])
            ]
        ));

        return commentContentDiv;
    }

    
};


var CommentTreeBuilder=function (comments) {

    this.comments=comments;
  this.appendCommentToTree=function (comment) {
        var commentElemet=comment.makeComment();
        if (comment.level==1){

            var expandDiv=$("<div>", {
                'class':'Expand'
            });
            var commentContentDiv=$("<div>", {
                "class": "comment_content",
                id:"commcont"+comment.id,
                'data-level': comment.level
            });
            var leafLi=$("<li>", {
                id: "comment"+comment.id,
                'class': "ExpandLeaf Node IsRoot"
            });


            $(commentContentDiv).append(commentElemet);
            $(leafLi).append(expandDiv);
            $(leafLi).append(commentContentDiv);
            $(comments).append(leafLi)



        }
        else{
            var parrent=$(comments).find('#comment'+comment.parent);
            if ($(parrent).find('ul.comment-container').length==0){
                if(!($(parrent).hasClass('Root'))){
                    $(parrent).addClass('Root').removeClass('ExpandLeaf');
                    var commentlevel=comment.level-1;
                    if (commentlevel<3){
                        $(parrent).addClass('ExpandOpen');
                    }
                    else $(parrent).addClass('ExpandClosed');
                }
                $(parrent).append($("<ul>",
                    {
                        "class": "comment-container",
                        id: "cont"+comment.parent
                    }))
            }

            var leafLiCh=$("<li>", {
                id: "comment"+comment.id,
                'class': "ExpandLeaf Node"
            });

            var expandDivCh=$("<div>", {
                'class':'Expand'
            });
            var commentContentDivCh=$("<div>", {
                "class": "comment_content",
                id:"commcont"+comment.id,
                'data-level': comment.level
            });

            var commentElemetCh=comment.makeComment();
            $(commentContentDivCh).append(commentElemetCh);
            $(leafLiCh).append(expandDivCh);
            $(leafLiCh).append(commentContentDivCh);
            $(comments).find('#cont'+comment.parent).append(leafLiCh);


        }
    }


};
$('document').ready(function(){
    $('table #editButton').on('click',function(event){
        // var modal = $('#editModal');
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function(khuvuc, status){
            $('#makhuEdit').val(khuvuc.id);
            $('#tensanEdit').val(khuvuc.tenKhuVuc);
            $('#diachiEdit').val(khuvuc.diaChi);

        });

        $('#editModal').modal("show");
        // modal.show();
    });
    //details

    $('#deleteButton').on('click', function (event) {
        event.preventDefault();


        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);


        $('#deleteModal').modal("show");
    });

    $('#deleteButton').on('click', function (event) {
        event.preventDefault();


        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);


        $('#deleteModal').modal("show");
    });
});


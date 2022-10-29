$('document').ready(function(){
    $('#editButton').on('click',function(event){
        // var modal = $('#editModal');
        event.preventDefault();

        var href = $(this).attr('href');

        $.get(href, function(sanbong, status){
            $('#masanEdit').val(sanbong.id);
            $('#tensanEdit').val(sanbong.tenSanBong);
            $('#makhuEdit').val(sanbong.MaKhuVuc);
            $('#anhsanEdit').val(sanbong.anhSan);
            $('#chieudaiEdit').val(sanbong.chieuDai);
            $('#chieurongEdit').val(sanbong.chieuRong);
            $('#diachiEdit').val(sanbong.diaChi);
            $('#dongiaEdit').val(sanbong.donGia);

        });

        $('#editModal').modal("show");
        // modal.show();
    });
    //details
    $('.table #detailsButton').on('click',function(event) {
        event.preventDefault();
        var href= $(this).attr('href');
        $.get(href, function(sanbong, status){
            $('#idDetails').val(sanbong.id);
            $('#tensanDetails').val(sanbong.tenSanBong);
            $('#ddlkhuvucDetails').val(sanbong.MaKhuVuc);
            $('#anhsanDetails').val(sanbong.anhSan);
            $('#chieudaiDetails').val(sanbong.chieuDai);
            $('#chieurongDetails').val(sanbong.chieuRong);
            $('#diachiDetails').val(sanbong.diaChi);
            $('#dongiaDetails').val(sanbong.donGia);

        });
        $('#detailsModal').modal("show");
        $('#closebtn').modal("hide");
    });

    $('#deleteButton').on('click', function (event) {
        event.preventDefault();


        var href = $(this).attr('href');
        $('#confirmDeleteButton').attr('href', href);


        $('#deleteModal').modal("Show");
    });
});


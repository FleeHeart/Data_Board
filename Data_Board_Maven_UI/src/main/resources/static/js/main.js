// 页面加载完成后执行
$(document).ready(function() {
    // 1. 加载并显示所有数据
    loadAds();

    // 2. 监听表单提交事件
    $('#adForm').on('submit', function(event) {
        event.preventDefault(); // 阻止表单默认提交行为

        // 收集表单数据，并转换成JSON对象
        const formData = {
            date: $('input[name="date"]').val(),
            cost: $('input[name="cost"]').val(),
            leadCount: parseInt($('input[name="leadCount"]').val()),
            messageCount: parseInt($('input[name="messageCount"]').val())
        };

        // 3. 发送POST请求到后端API
        $.ajax({
            type: 'POST',
            url: '/api/ads', // 这是你后端Controller的地址
            contentType: 'application/json', // 告诉后端发送的是JSON
            data: JSON.stringify(formData), // 将JS对象转换为JSON字符串
            success: function(data) {
                // 请求成功
                alert('添加成功！');
                $('#adForm')[0].reset(); // 清空表单
                loadAds(); // 重新加载表格数据
            },
            error: function() {
                // 请求失败
                alert('添加失败，请检查控制台日志！');
            }
        });
    });
});

// 加载广告数据的函数
function loadAds() {
    $.ajax({
        type: 'GET',
        url: '/api/ads', // 调用GET接口
        success: function(date) {
            // 清空表格现有数据
            $('#adTable tbody').empty();
            // 遍历返回的数据数组
            $.each(date, function(index, ad) {
                // 将每条数据追加为表格的一行
                $('#adTable tbody').append(
                    '<tr>' +
                    '   <td>' + ad.id + '</td>' +
                    '   <td>' + ad.date + '</td>' +
                    '   <td>' + ad.cost + '</td>' +
                    '   <td>' + ad.leadCount + '</td>' +
                    '   <td>' + ad.messageCount + '</td>' +
                    '</tr>'
                );
            });
        },
        error: function() {
            alert('加载数据失败！');
        }
    });
}
var settleUtil = settleUtil || {};

settleUtil.prototype = {
    //元素的展示
    show: function (obj, d) {
        obj.click(function () {
            d.show();
        });
    },
    //元素的隐藏
    hide: function (obj, d) {
        obj.click(function () {
            d.hide();
        });
    },
    //时间戳转化为 日期格式
    timestampToTime: function (timestamp) {
        var date = new Date(timestamp);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
        Y = date.getFullYear() + '-';
        M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        D = date.getDate() + ' ';
        h = date.getHours() + ':';
        m = date.getMinutes() + ':';
        s = date.getSeconds();
        return Y + M + D + h + m + s;
    },
    //获取日月 periodType 日or月 前多少或者后多少天/月
    getDate: function (periodType, currentIndex) {
        var date = new Date;
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var currentMonth = month;
        var currentDay = date.getDate();
        if ("MONTH" == periodType) {
            month = month - currentIndex;
            if (month <= 0) {
                currentIndex = currentMonth;
                return year.toString() + "01";
            } else if (0 < parseInt(month) && parseInt(month) < 10) {
                return (year.toString() + "0" + month.toString());
            } else if (parseInt(month) > 12) {
                currentIndex = currentMonth - 12;
                return year.toString() + "12";
            } else {
                return year.toString() + month.toString();
            }
        } else {
            var day = date.getDate() - currentIndex;
            month = month < 10 ? "0" + month : month;
            if (day < 1) {
                currentIndex = currentDay;
                return year.toString() + month.toString() + "01";
            } else if (0 < parseInt(day) && parseInt(day) < 10) {
                return year.toString() + month.toString() + "0" + day;
            } else if (day > 31) {
                currentIndex = currentMonth - 31;
                return year.toString() + month.toString() + "31";
            } else {
                return year.toString() + month.toString() + day;
            }
        }
    },
    endWith: function (str, endStr) {
        var d = str.length - endStr.length;
        return (d >= 0 && str.lastIndexOf(endStr) == d)
    },
    openPostWindow: function (url, params) {
        var tempForm = document.createElement("form");
        tempForm.id = "tempForm1";
        tempForm.method = "post";
        tempForm.action = url;
        tempForm.target="_blank"; //打开新页面
        var hideInput1 = document.createElement("input");
        hideInput1.type = "hidden";
        hideInput1.name="params"; //后台要接受这个参数来取值
        hideInput1.value = params; //后台实际取到的值
        tempForm.appendChild(hideInput1);
        if(document.all){
            tempForm.attachEvent("onsubmit",function(){});        //IE
        }else{
            var subObj = tempForm.addEventListener("submit",function(){},false);    //firefox
        }
        document.body.appendChild(tempForm);
        if(document.all){
            tempForm.fireEvent("onsubmit");
        }else{
            tempForm.dispatchEvent(new Event("submit"));
        }
        tempForm.submit();
        document.body.removeChild(tempForm);
    }
}
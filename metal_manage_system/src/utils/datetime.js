export function GetDateTime() {
 
    var getDate = new Date;

    let dateYear = getDate.getFullYear();             //获取年 

    let dateMonth = getDate.getMonth() + 1;               //获取月  

    let dateDate = getDate.getDate();                 //获取当日

    let dateHours = getDate.getHours();               //获取小时

    let dateMinutes = getDate.getMinutes();           //获取分钟

    let dateSeconds = getDate.getSeconds();           //获取秒

    dateMonth = dateMonth < 10 ? "0" + dateMonth : dateMonth;

    dateDate = dateDate < 10 ? "0" + dateDate : dateDate;

    dateHours = dateHours < 10 ? "0" + dateHours : dateHours;

    dateMinutes = dateMinutes < 10 ? "0" + dateMinutes : dateMinutes;

    dateSeconds = dateSeconds < 10 ? "0" + dateSeconds : dateSeconds;

    return dateYear + "-" + dateMonth + "-" + dateDate + " " + dateHours + ":" + dateMinutes + ":" + dateSeconds;

}
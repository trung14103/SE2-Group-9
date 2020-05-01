function openNav() {
  document.getElementById("mySidenav").style.width = "250px";
  document.getElementsByClassName("container-fluid").style.marginLeft = "250px";
  document.body.style.backgroundColor = "rgba(0,0,0,0.4)";
}

function closeNav() {
  document.getElementById("mySidenav").style.width = "0";
  document.getElementsByClassName("container-fluid").style.marginLeft = "0";
  document.body.style.backgroundColor = "white";
}

window.onload = function() {

  var chart = new CanvasJS.Chart("chartContainer", {
    animationEnabled: true,
    title: {
      text: "Desktop Search Engine Market Share - 2016"
    },
    data: [{
      type: "pie",
      startAngle: 240,
      yValueFormatString: "##0.00\"%\"",
      indexLabel: "{label} {y}",
      dataPoints: [
        {y: 79.45, label: "Google"},
        {y: 7.31, label: "Bing"},
        {y: 7.06, label: "Baidu"},
        {y: 4.91, label: "Yahoo"},
        {y: 1.26, label: "Others"}
      ]
    }]
  });
  chart.render();
  
  }
$(document).ready(function () {
  // Set new default font family and font color to mimic Bootstrap's default styling
  (Chart.defaults.global.defaultFontFamily = "Nunito"), '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
  Chart.defaults.global.defaultFontColor = "#858796";

  let chartData = [];
  let chartLabels = [];
  $("#myPieChart > chart-data").each(function (i, elem) {
    chartLabels.push($(elem).attr("label"));
    chartData.push($(elem).attr("data"));
  });

  // Pie Chart Example
  var ctx = document.getElementById("myPieChart");
  var myPieChart = new Chart(ctx, {
    type: "doughnut",
    data: {
      labels: chartLabels,
      datasets: [
        {
          data: chartData,
          backgroundColor: ["rgba(201, 203, 207, 0.2)", "rgba(75, 192, 192, 0.2)", "rgba(54, 162, 235, 0.2)"],
          hoverBackgroundColor: ["#2e59d9", "#17a673", "#2c9faf"],
          hoverBorderColor: "rgba(234, 236, 244, 1)",
        },
      ],
    },
    options: {
      maintainAspectRatio: false,
      tooltips: {
        backgroundColor: "rgb(255,255,255)",
        bodyFontColor: "#858796",
        borderColor: "#dddfeb",
        borderWidth: 1,
        xPadding: 15,
        yPadding: 15,
        displayColors: false,
        caretPadding: 10,
      },
      legend: {
        display: false,
      },
      cutoutPercentage: 80,
    },
  });
});

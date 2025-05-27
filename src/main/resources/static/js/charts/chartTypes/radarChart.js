export function getRadarChartConf(labels, values, title) {
  const colors = [
    'rgba(255, 99, 132, 0.6)',
    'rgba(54, 162, 235, 0.6)',
    'rgba(255, 206, 86, 0.6)',
    'rgba(75, 192, 192, 0.6)',
    'rgba(153, 102, 255, 0.6)',
    'rgba(255, 159, 64, 0.6)',
  ];
  const borders = colors.map((c) => c.replace('0.6', '1'));

  return {
    data: {
      labels: labels,
      datasets: [
        {
          label: title,
          data: values,
          backgroundColor: colors[0],
          borderColor: borders[0],
          borderWidth: 2,
          pointBackgroundColor: colors[0],
          pointBorderColor: borders[0],
          pointHoverBackgroundColor: borders[0],
          pointHoverBorderColor: colors[0],
          fill: true,
        },
      ],
    },
    options: {
      responsive: true,
      scales: {
        r: {
          beginAtZero: true,
          // Puedes configurar el ángulo y la grilla aquí si quieres
        },
      },
    },
  };
}

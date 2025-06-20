export function getLineChartConf(labels, values, title) {
  const colors = [
    'rgba(52, 113, 235, 0.6)',
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
          fill: false,
          borderColor: borders[0],
          backgroundColor: colors[0],
          tension: 0.3, 
          pointRadius: 5,
          pointHoverRadius: 7,
          borderWidth: 2,
        },
      ],
    },
    options: {
      responsive: true,
      scales: {
        y: { beginAtZero: true },
      },
    },
  };
}

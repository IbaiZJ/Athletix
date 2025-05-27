export class ChartBuilder {
  static createBar(canvasId, config) {
    const ctx = document.getElementById(canvasId).getContext('2d');
    return new Chart(ctx, {
      type: 'bar',
      data: config.data,
      options: config.options,
    });
  }

  static createLine(canvasId, config) {
    const ctx = document.getElementById(canvasId).getContext('2d');
    return new Chart(ctx, {
      type: 'line',
      data: config.data,
      options: config.options,
    });
  }

  static createRadar(canvasId, config) {
    const ctx = document.getElementById(canvasId).getContext('2d');
    return new Chart(ctx, {
      type: 'radar',
      data: config.data,
      options: config.options,
    });
  }
}

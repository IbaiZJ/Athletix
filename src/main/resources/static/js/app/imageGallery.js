import PhotoSwipeLightbox from 'https://unpkg.com/photoswipe@5/dist/photoswipe-lightbox.esm.min.js';

document.querySelectorAll('.pswp-gallery').forEach((galleryEl) => {
  const lightbox = new PhotoSwipeLightbox({
    gallery: galleryEl,
    children: 'a',
    arrowPrev: true,
    arrowNext: true,
    pswpModule: () => import('https://unpkg.com/photoswipe@5/dist/photoswipe.esm.min.js'),
  });
  lightbox.init();

  // Ajustar columnas según cantidad de hijos
  const childrenCount = galleryEl.querySelectorAll('a').length;
  if (childrenCount === 1) {
    galleryEl.classList.add('grid-cols-1');
    galleryEl.classList.remove('grid-cols-2', 'grid-cols-3');
  } else if (childrenCount === 2) {
    galleryEl.classList.add('grid-cols-2');
    galleryEl.classList.remove('grid-cols-1', 'grid-cols-3');
  } else {
    galleryEl.classList.add('grid-cols-3');
    galleryEl.classList.remove('grid-cols-1', 'grid-cols-2');
  }

  // Opcional: ocultar imágenes extra si quieres solo mostrar máximo 3
  galleryEl.querySelectorAll('a').forEach((child, index) => {
    if (index > 2) {
      child.classList.add('hidden');
    }
  });
});

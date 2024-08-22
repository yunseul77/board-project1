// script.js

document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('search-button').addEventListener('click', searchPosts);
});

function searchPosts() {
  const keyword = document.getElementById('search-input').value;
  const boardId = /* Thymeleaf 변수로 현재 게시판 ID를 전달 */;

  fetch(`/api/boards/${boardId}/posts?keyword=${encodeURIComponent(keyword)}`)
    .then(response => response.json())
    .then(data => {
      const postList = document.getElementById('post-list');
      const pagination = document.getElementById('pagination');

      // Clear previous results
      postList.innerHTML = '';
      pagination.innerHTML = '';

      if (data.posts.length > 0) {
        data.posts.forEach(post => {
          const postItem = document.createElement('a');
          postItem.className = 'board-item list-group-item p-3 list-group-item-action';
          postItem.href = `/posts/${post.id}`;
          postItem.innerHTML = `
            <h4 class="fw-bold mb-1">${post.title}</h4>
            <small class="text-secondary">${post.createdAt}</small>
          `;
          postList.appendChild(postItem);
        });

        // Pagination
        for (let i = 0; i < data.totalPages; i++) {
          const pageItem = document.createElement('li');
          pageItem.className = 'page-item';
          if (i === data.currentPage) pageItem.classList.add('active');
          pageItem.innerHTML = `
            <a class="page-link" href="?page=${i}&keyword=${encodeURIComponent(keyword)}">${i + 1}</a>
          `;
          pagination.appendChild(pageItem);
        }
      } else {
        postList.innerHTML = '<span class="text-center">검색 결과가 없습니다.</span>';
      }
    })
    .catch(error => console.error('Error fetching posts:', error));
}

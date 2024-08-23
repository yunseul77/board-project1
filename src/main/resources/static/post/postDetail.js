document.addEventListener('DOMContentLoaded', function() {
    // 게시글 ID를 URL에서 가져옵니다.
    const urlParams = new URLSearchParams(window.location.search);
    const postId = urlParams.get('postId');

    // 게시글 상세 정보를 가져오는 API 요청
    fetch(`http://localhost:8080/post/detail/${postId}`)
        .then(response => response.json())
        .then(data => {
            // 게시글 정보 표시
            document.getElementById('post-title').textContent = data.postDetail.title;
            document.getElementById('post-created-at').textContent = data.postDetail.createdAt;
            document.getElementById('post-modified-at').textContent = data.postDetail.modifiedAt;
            document.getElementById('post-content').innerHTML = data.postDetail.content;

            // 댓글 정보 표시
            const commentsList = document.getElementById('comments-list');
            if (data.comments && data.comments.content.length > 0) {
                data.comments.content.forEach(comment => {
                    const commentElement = document.createElement('div');
                    commentElement.className = 'comment';
                    commentElement.innerHTML = `
                        <p class="comment-author">${comment.author || 'Anonymous'}</p>
                        <p class="comment-content">${comment.content || 'No content'}</p>
                    `;
                    commentsList.appendChild(commentElement);
                });
            } else {
                commentsList.innerHTML = '<p>댓글이 없습니다.</p>';
            }
        })
        .catch(error => {
            console.error('Error fetching post detail:', error);
            document.querySelector('.post-container').innerHTML = '<p>게시글을 가져오는 데 오류가 발생했습니다.</p>';
        });
});
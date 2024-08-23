document.addEventListener('DOMContentLoaded', function() {
    function fetchPosts(page = 0) {
        fetch(`http://localhost:8080/post/postList?page=${page}`)
            .then(response => response.json())
            .then(data => {
                const postList = document.getElementById('post-list');
                const pagination = document.getElementById('pagination');

                postList.innerHTML = '';
                pagination.innerHTML = '';

                if (data && data.content && data.content.length > 0) {
                    data.content.forEach(post => {
                        const postItem = document.createElement('a');
                        postItem.className = 'list-group-item list-group-item-action';
                        postItem.href = `/post/detail/${post.id}`;
                        postItem.innerHTML = `
                            <h4>${post.title}</h4>
                            <p>${post.createdAt || '업로드 일자'}</p>
                        `;
                        postList.appendChild(postItem);
                    });

                    for (let i = 0; i < data.totalPages; i++) {
                        const pageItem = document.createElement('li');
                        pageItem.className = 'page-item';
                        if (i === data.number) pageItem.classList.add('active');

                        const pageLink = document.createElement('a');
                        pageLink.className = 'page-link';
                        pageLink.href = `#`;
                        pageLink.innerText = i + 1;

                        pageLink.addEventListener('click', function(event) {
                            event.preventDefault();
                            fetchPosts(i);
                        });

                        pageItem.appendChild(pageLink);
                        pagination.appendChild(pageItem);
                    }
                } else {
                    postList.innerHTML = '<p>게시글이 없습니다.</p>';
                }
            })
            .catch(error => {
                console.error('게시글을 가져오는 중 오류 발생:', error);
                document.getElementById('post-list').innerHTML = '<p>게시글을 가져오는 데 오류가 발생했습니다.</p>';
            });
    }

    fetchPosts();

    const params = new URLSearchParams(window.location.search);
    const page = params.get('page');
    if (page) {
        fetchPosts(parseInt(page, 10));
    }
});

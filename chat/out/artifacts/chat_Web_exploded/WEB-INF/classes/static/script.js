// 获取元素
const messageInput = document.getElementById('message-input');
const submitBtn = document.getElementById('submit-btn');
const chatDisplay = document.querySelector('.chat-display');

// 添加事件监听器
submitBtn.addEventListener('click', sendMessage);

// 定义函数：发送消息
function sendMessage() {
    // 获取输入的消息内容
    const message = messageInput.value;

    // 创建一个新的消息元素
    const newMessage = document.createElement('div');
    newMessage.classList.add('message');
    if(message !== ''){
        const message = {
            sender_id: 1,
            receiver_id: 2,
            group_id: 0,
            content: message,
            status: 'sent'
        };

        // 获取当前用户的用户名
        const user_name = 'User';
        //
        // // 设置消息的样式
        if (user_name === 'User') {
            // 用户的消息样式
            newMessage.style.backgroundColor = '#069dcb';
            newMessage.style.color = '#ffffff';
            newMessage.style.alignSelf = 'flex-end'; // 用户消息靠右对齐
        } else {
            // 对方的消息样式
            newMessage.style.backgroundColor = '#c4c4c4';
            newMessage.style.color = '#333333';
            newMessage.style.alignSelf = 'flex-start'; // 对方消息靠左对齐
        }

        // 设置消息内容和样式

        newMessage.innerHTML = message;
        newMessage.style.padding = '5px 10px';
        newMessage.style.marginBottom = '10px';
        newMessage.style.borderRadius = '10px';
        newMessage.style.maxWidth = '80%';
        newMessage.style.wordWrap = 'break-word';
        newMessage.style.position = 'relative'; // 为伪元素定位做准备

        // 小箭头
        const arrow = document.createElement('div');
        arrow.style.width = '0';
        arrow.style.height = '0';
        arrow.style.borderStyle = 'solid';
        arrow.style.position = 'absolute';
        arrow.style.top = '10px'; // 调整箭头的位置
        arrow.style.borderWidth = '5px 0 5px 5px';
        arrow.style.borderColor = 'transparent transparent transparent #069dbc';
        arrow.style.right = '-5px'; // 调整箭头的位置

        // 插入空白行
        const emptyLine = document.createElement('div');
        emptyLine.style.height = '10px'; // 设置空白行的高度
        emptyLine.style.backgroundColor = 'transparent'; // 设置背景色为透明

        newMessage.appendChild(arrow);
        chatDisplay.appendChild(emptyLine);
        chatDisplay.appendChild(newMessage);

        // 清空输入框
        messageInput.value = '';

        // 发送消息到服务器保存
        saveMessageToServer(message);
    }
}


// 定义函数：保存消息到服务器
function saveMessageToServer(message) {
    fetch('/saveMessage', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            message
        }),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('网络响应问题');
            }
            return response.json();
        })
        .then(data => {
            console.log('消息已保存:', data);
        })
        .catch(error => {
            console.error('消息保存失败:', error);
        });
}
document.addEventListener("DOMContentLoaded", function () {
    const taskInput = document.getElementById("taskInput");
    const addTaskButton = document.getElementById("addTaskButton");
    const taskList = document.getElementById("taskList");

    function loadTasks() {
        const tasks = JSON.parse(localStorage.getItem("tasks")) || [];
        taskList.innerHTML = "";
        tasks.forEach((task, index) => {
            const listItem = document.createElement("li");
            listItem.textContent = task.text;
            listItem.className = task.completed ? "completed" : "";
            
            const deleteButton = document.createElement("button");
            deleteButton.textContent = "Delete";
            deleteButton.onclick = function () {
                deleteTask(index);
            };

            const toggleButton = document.createElement("button");
            toggleButton.textContent = task.completed ? "Undo" : "Complete";
            toggleButton.onclick = function () {
                toggleTask(index);
            };
            
            listItem.appendChild(toggleButton);
            listItem.appendChild(deleteButton);
            taskList.appendChild(listItem);
        });
    }

    function saveTasks(tasks) {
        localStorage.setItem("tasks", JSON.stringify(tasks));
    }

    function addTask() {
        const taskText = taskInput.value.trim();
        if (taskText === "") return;
        
        const tasks = JSON.parse(localStorage.getItem("tasks")) || [];
        tasks.push({ text: taskText, completed: false });
        saveTasks(tasks);
        loadTasks();
        taskInput.value = "";
    }

    function deleteTask(index) {
        const tasks = JSON.parse(localStorage.getItem("tasks")) || [];
        tasks.splice(index, 1);
        saveTasks(tasks);
        loadTasks();
    }

    function toggleTask(index) {
        const tasks = JSON.parse(localStorage.getItem("tasks")) || [];
        tasks[index].completed = !tasks[index].completed;
        saveTasks(tasks);
        loadTasks();
    }

    addTaskButton.addEventListener("click", addTask);
    loadTasks();
});
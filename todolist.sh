#!/bin/bash

TODO_FILE="todo.txt"

# Ensure todo file exists
touch "$TODO_FILE"

function show_menu() {
  echo "To-Do List Manager"
  echo "1) View tasks"
  echo "2) Add task"
  echo "3) Remove task"
  echo "4) Exit"
  echo -n "Choose an option: "
}

function view_tasks() {
  if [[ ! -s $TODO_FILE ]]; then
    echo "No tasks found."
  else
    nl -w2 -s'. ' "$TODO_FILE"
  fi
}

function add_task() {
  echo -n "Enter task description: "
  read task
  if [[ -n $task ]]; then
    echo "$task" >> "$TODO_FILE"
    echo "Task added."
  else
    echo "No task entered."
  fi
}

function remove_task() {
  view_tasks
  echo -n "Enter task number to remove: "
  read task_num
  if [[ $task_num =~ ^[0-9]+$ ]] && (( task_num > 0 )); then
    sed -i "${task_num}d" "$TODO_FILE"
    echo "Task removed."
  else
    echo "Invalid task number."
  fi
}

while true; do
  show_menu
  read choice
  case $choice in
    1) view_tasks ;;
    2) add_task ;;
    3) remove_task ;;
    4) exit ;;
    *) echo "Invalid option." ;;
  esac
  echo
done

package com.iesebre.dam2.amayor.todos;

import com.google.gson.Gson;

/**
 * Created by albert on 13/11/15.
 */
public class TodoItem {

        public static enum Color {
            RED, BLUE, GREEN;
        }

        private String text;
        private Color color;
        private boolean checked;

        public TodoItem(){
            this.text = "";
            this.color = Color.BLUE;
            this.checked = false;
        }

        public TodoItem(TodoItem ti){
            this.text = ti.getText();
            this.color = ti.getColor();
            this.checked = ti.getChecked();
        }

        static public TodoItem create(String serializedData) {
            Gson gson = new Gson();
            return gson.fromJson(serializedData, TodoItem.class);
        }

        public String serialize() {
            Gson gson = new Gson();
            return gson.toJson(this);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Color getColor() {
            return color;
        }

        public void setColor(Color color) {
            this.color = color;
        }

        public boolean getChecked(){
            return checked;
        }

        public void setChecked(boolean c){
            checked = c;
        }

        public void toggleChecked(){
            checked = !checked;
        }

        @Override
        public String toString() {
            return "[ text: " + text + ",  color: " + color + ", checked: " + checked + "]";
        }
}

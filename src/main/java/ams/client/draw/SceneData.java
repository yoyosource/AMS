package ams.client.draw;

import java.awt.*;

public abstract class SceneData {

    public int getWidth() {
        throw new UnsupportedOperationException();
    }
    public int getHeight() {
        throw new UnsupportedOperationException();
    }
    public Graphics2D getGraphics2D() {
        throw new UnsupportedOperationException();
    }


    public Point getMouseLocation() {
        throw new UnsupportedOperationException();
    }
    public double getMouseWheelMovement() {
        throw new UnsupportedOperationException();
    }

    public int getButton() {
        throw new UnsupportedOperationException();
    }
    public boolean isClick() {
        throw new UnsupportedOperationException();
    }
    public boolean isPress() {
        throw new UnsupportedOperationException();
    }
    public boolean isRelease() {
        throw new UnsupportedOperationException();
    }
    public boolean isEnter() {
        throw new UnsupportedOperationException();
    }
    public boolean isExit() {
        throw new UnsupportedOperationException();
    }

    public boolean isMove() {
        throw new UnsupportedOperationException();
    }
    public boolean isDrag() {
        throw new UnsupportedOperationException();
    }


    public char getTyped() {
        throw new UnsupportedOperationException();
    }
    public int modifier() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder();
        st.append("SceneData[");
        boolean b = false;
        try {
            Object object = getWidth();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("width=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = getHeight();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("height=").append(object);
        } catch (UnsupportedOperationException e) {

        }

        try {
            Object object = getMouseLocation();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("mouseLocation=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = getMouseWheelMovement();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("mouseWheelMovement=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = getButton();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("button=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isClick();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("click=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isPress();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("press=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isRelease();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("release=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isEnter();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("enter=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isExit();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("exit=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isMove();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("move=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = isDrag();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("drag=").append(object);
        } catch (UnsupportedOperationException e) {

        }

        try {
            Object object = getTyped();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("typed=").append(object);
        } catch (UnsupportedOperationException e) {

        }
        try {
            Object object = modifier();
            if (b) {
                st.append(", ");
            }
            b = true;
            st.append("modifier=").append(object);
        } catch (UnsupportedOperationException e) {

        }

        st.append("]");
        return st.toString();
    }
}

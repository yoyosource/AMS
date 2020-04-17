package ams.client.draw;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class DrawManager extends JComponent {

    private static Map<String, Scene> sceneMap = new HashMap<>();

    private static Scene currentScene = null;

    public static void addScene(Scene scene) {
        if (sceneMap.containsKey(scene.getName())) {
            return;
        }
        sceneMap.put(scene.getName(), scene);
    }

    public static void changeScene(String name) {
        currentScene = sceneMap.get(name);
        if (currentScene != null) {
            currentScene.init();
        }
    }

    public static void changeScene(Scene scene) {
        addScene(scene);
        changeScene(scene.getName());
    }

    private Point mouse = new Point(-10000, -10000);

    public KeyListener keyListener = new KeyListener() {
        @Override
        public void keyTyped(KeyEvent e) {
            SceneData sceneData = new SceneData() {
                @Override
                public char getTyped() {
                    return e.getKeyChar();
                }

                @Override
                public boolean isClick() {
                    return true;
                }

                @Override
                public int modifier() {
                    return e.getModifiersEx();
                }
            };
            System.out.println(sceneData);
        }

        @Override
        public void keyPressed(KeyEvent e) {

        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    };

    public DrawManager() {
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public int getButton() {
                        return e.getButton();
                    }

                    @Override
                    public boolean isClick() {
                        return true;
                    }
                };
                System.out.println(sceneData);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public int getButton() {
                        return e.getButton();
                    }

                    @Override
                    public boolean isPress() {
                        return true;
                    }
                };
                System.out.println(sceneData);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public int getButton() {
                        return e.getButton();
                    }

                    @Override
                    public boolean isRelease() {
                        return true;
                    }
                };
                System.out.println(sceneData);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public boolean isEnter() {
                        return true;
                    }
                };
                System.out.println(sceneData);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public boolean isExit() {
                        return true;
                    }
                };
                System.out.println(sceneData);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public double getMouseWheelMovement() {
                        return e.getScrollAmount();
                    }
                };
                System.out.println(sceneData);
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public boolean isDrag() {
                        return true;
                    }
                };
                mouse = e.getPoint();
                System.out.println(sceneData);
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                SceneData sceneData = new SceneData() {
                    @Override
                    public Point getMouseLocation() {
                        return e.getPoint();
                    }

                    @Override
                    public boolean isMove() {
                        return true;
                    }
                };
                mouse = e.getPoint();
                System.out.println(sceneData);
            }
        };
        addMouseMotionListener(mouseAdapter);
        addMouseListener(mouseAdapter);
        addMouseWheelListener(mouseAdapter);

        addKeyListener(keyListener);
        validate();
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (currentScene != null) {
            currentScene.draw(new SceneData() {
                @Override
                public Graphics2D getGraphics2D() {
                    return (Graphics2D) g;
                }

                @Override
                public int getWidth() {
                    return g.getClipBounds().width;
                }

                @Override
                public int getHeight() {
                    return g.getClipBounds().height;
                }

                @Override
                public Point getMouseLocation() {
                    return mouse;
                }
            });
        } else {
            ((Graphics2D)g).fillRect(0, 0, g.getClipBounds().width, g.getClipBounds().height);
        }
    }

}

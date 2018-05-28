package maltar.apps.remoteMouseServer.utilities;

import maltar.apps.remoteMouseServer.params.ActionKey;
import maltar.apps.remoteMouseServer.params.ClientKeyEvents;
import org.json.JSONObject;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Maltar on 14.2.2018..
 */
public class ActionHandler {
    private ActionHandler(){

    }

    private static void handleActionMove(String execute, String description) {
        int xBeginIndex = description.indexOf("x") + 2;
        int xEndIndex = description.indexOf(":");

        int yBeginIndex = xEndIndex + 3;
        int yEndIndex = description.length();

        String sX = description.substring(xBeginIndex, xEndIndex);
        String sY = description.substring(yBeginIndex, yEndIndex);
        int moveX = Integer.parseInt(sX);
        int moveY = Integer.parseInt(sY);

        int maxDif = Math.abs(moveX) > Math.abs(moveY) ? Math.abs(moveX) : Math.abs(moveY);

        Point currentPoint = MouseInfo.getPointerInfo().getLocation();
        int currentX = currentPoint.x;
        int currentY = currentPoint.y;

        int newX = currentX + moveX;
        int newY = currentY + moveY;

        int stepX = currentX > newX ? -1 : 1;
        int stepY = currentY > newY ? -1 : 1;

        try {
            Robot robot = new Robot();
            for (int i = 0; i < maxDif; i++) {
                if (currentX != newX) {
                    currentX += stepX;
                }
                if (currentY != newY) {
                    currentY += stepY;
                }
                robot.mouseMove(currentX, currentY);
            }
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    private static void handleActionClick(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case ActionKey.ACTION_SCROLL_UP:
                robot.mouseWheel(-1);
                break;
            case ActionKey.ACTION_SCROLL_DOWN:
                robot.mouseWheel(1);
                break;
            case ActionKey.ACTION_CLICKER:
                double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                if (mouseX > -1910 && mouseX < -1656 && mouseY > 475 && mouseY < 755) {
                    for (int i = 0; i < 1000000000; i++) {
                        mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                        mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                        if (!(mouseX > -1910 && mouseX < -1656 && mouseY > 475 && mouseY < 755)) {
                            break;
                        }
                        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                        try {
                            Thread.sleep(1);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
            case ActionKey.ACTION_CLICKER_PR:
                for (int i = 0; i < 1000000000; i++) {
                    mouseX = MouseInfo.getPointerInfo().getLocation().getX();
                    mouseY = MouseInfo.getPointerInfo().getLocation().getY();
                    robot.mouseMove(-1790, 622);
                    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                    robot.mouseMove((int)mouseX, (int)mouseY);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case ActionKey.ACTION_UP:
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                break;
            case ActionKey.ACTION_LEFT:
                robot.keyPress(KeyEvent.VK_LEFT);
                robot.keyRelease(KeyEvent.VK_LEFT);
                break;
            case ActionKey.ACTION_RIGHT:
                robot.keyPress(KeyEvent.VK_RIGHT);
                robot.keyRelease(KeyEvent.VK_RIGHT);
                break;
            case ActionKey.ACTION_DOWN:
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                break;
            case ActionKey.ACTION_ENTER:
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                break;
        }
    }

    private static void handleActionPress(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case ActionKey.ACTION_LEFT_MOUSE:
                robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                System.out.println(MouseInfo.getPointerInfo().getLocation());
                break;
            case ActionKey.ACTION_RIGHT_MOUSE:
                robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                break;
            case ActionKey.ACTION_MOVE_LEFT:
                robot.keyPress(KeyEvent.VK_LEFT);
                break;
            case ActionKey.ACTION_MOVE_RIGHT:
                robot.keyPress(KeyEvent.VK_RIGHT);
                break;
            case ActionKey.ACTION_JUMP:
                robot.keyPress(KeyEvent.VK_SPACE);
                break;
            case ActionKey.ACTION_ESC:
                robot.keyPress(KeyEvent.VK_ESCAPE);
                break;
            case ActionKey.ACTION_ENTER:
                robot.keyPress(KeyEvent.VK_ENTER);
                break;
            case ActionKey.ACTION_SWIPE:
                robot.keyPress(KeyEvent.VK_CONTROL);
                break;
        }
    }

    private static void handleActionEnter(String execute, String description) {
        int keyEvent = Integer.parseInt(description);
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (keyEvent) {
            case ClientKeyEvents.KEYCODE_A:
                robot.keyPress(KeyEvent.VK_A);
                robot.keyRelease(KeyEvent.VK_A);
                break;
            case ClientKeyEvents.KEYCODE_B:
                robot.keyPress(KeyEvent.VK_B);
                robot.keyRelease(KeyEvent.VK_B);
                break;
            case ClientKeyEvents.KEYCODE_C:
                robot.keyPress(KeyEvent.VK_C);
                robot.keyRelease(KeyEvent.VK_C);
                break;
            case ClientKeyEvents.KEYCODE_D:
                robot.keyPress(KeyEvent.VK_D);
                robot.keyRelease(KeyEvent.VK_D);
                break;
            case ClientKeyEvents.KEYCODE_E:
                robot.keyPress(KeyEvent.VK_E);
                robot.keyRelease(KeyEvent.VK_E);
                break;
            case ClientKeyEvents.KEYCODE_F:
                robot.keyPress(KeyEvent.VK_F);
                robot.keyRelease(KeyEvent.VK_F);
                break;
            case ClientKeyEvents.KEYCODE_G:
                robot.keyPress(KeyEvent.VK_G);
                robot.keyRelease(KeyEvent.VK_G);
                break;
            case ClientKeyEvents.KEYCODE_H:
                robot.keyPress(KeyEvent.VK_H);
                robot.keyRelease(KeyEvent.VK_H);
                break;
            case ClientKeyEvents.KEYCODE_I:
                robot.keyPress(KeyEvent.VK_I);
                robot.keyRelease(KeyEvent.VK_I);
                break;
            case ClientKeyEvents.KEYCODE_J:
                robot.keyPress(KeyEvent.VK_J);
                robot.keyRelease(KeyEvent.VK_J);
                break;
            case ClientKeyEvents.KEYCODE_K:
                robot.keyPress(KeyEvent.VK_K);
                robot.keyRelease(KeyEvent.VK_K);
                break;
            case ClientKeyEvents.KEYCODE_L:
                robot.keyPress(KeyEvent.VK_L);
                robot.keyRelease(KeyEvent.VK_L);
                break;
            case ClientKeyEvents.KEYCODE_M:
                robot.keyPress(KeyEvent.VK_M);
                robot.keyRelease(KeyEvent.VK_M);
                break;
            case ClientKeyEvents.KEYCODE_N:
                robot.keyPress(KeyEvent.VK_N);
                robot.keyRelease(KeyEvent.VK_N);
                break;
            case ClientKeyEvents.KEYCODE_O:
                robot.keyPress(KeyEvent.VK_O);
                robot.keyRelease(KeyEvent.VK_O);
                break;
            case ClientKeyEvents.KEYCODE_P:
                robot.keyPress(KeyEvent.VK_P);
                robot.keyRelease(KeyEvent.VK_P);
                break;
            case ClientKeyEvents.KEYCODE_Q:
                robot.keyPress(KeyEvent.VK_Q);
                robot.keyRelease(KeyEvent.VK_Q);
                break;
            case ClientKeyEvents.KEYCODE_R:
                robot.keyPress(KeyEvent.VK_R);
                robot.keyRelease(KeyEvent.VK_R);
                break;
            case ClientKeyEvents.KEYCODE_S:
                robot.keyPress(KeyEvent.VK_S);
                robot.keyRelease(KeyEvent.VK_S);
                break;
            case ClientKeyEvents.KEYCODE_T:
                robot.keyPress(KeyEvent.VK_T);
                robot.keyRelease(KeyEvent.VK_T);
                break;
            case ClientKeyEvents.KEYCODE_U:
                robot.keyPress(KeyEvent.VK_U);
                robot.keyRelease(KeyEvent.VK_U);
                break;
            case ClientKeyEvents.KEYCODE_V:
                robot.keyPress(KeyEvent.VK_V);
                robot.keyRelease(KeyEvent.VK_V);
                break;
            case ClientKeyEvents.KEYCODE_W:
                robot.keyPress(KeyEvent.VK_W);
                robot.keyRelease(KeyEvent.VK_W);
                break;
            case ClientKeyEvents.KEYCODE_X:
                robot.keyPress(KeyEvent.VK_X);
                robot.keyRelease(KeyEvent.VK_X);
                break;
            case ClientKeyEvents.KEYCODE_Y:
                robot.keyPress(KeyEvent.VK_Y);
                robot.keyRelease(KeyEvent.VK_Y);
                break;
            case ClientKeyEvents.KEYCODE_Z:
                robot.keyPress(KeyEvent.VK_Z);
                robot.keyRelease(KeyEvent.VK_Z);
                break;
            case ClientKeyEvents.KEYCODE_0:
                robot.keyPress(KeyEvent.VK_0);
                robot.keyRelease(KeyEvent.VK_0);
                break;
            case ClientKeyEvents.KEYCODE_1:
                robot.keyPress(KeyEvent.VK_1);
                robot.keyRelease(KeyEvent.VK_1);
                break;
            case ClientKeyEvents.KEYCODE_2:
                robot.keyPress(KeyEvent.VK_2);
                robot.keyRelease(KeyEvent.VK_2);
                break;
            case ClientKeyEvents.KEYCODE_3:
                robot.keyPress(KeyEvent.VK_3);
                robot.keyRelease(KeyEvent.VK_3);
                break;
            case ClientKeyEvents.KEYCODE_4:
                robot.keyPress(KeyEvent.VK_4);
                robot.keyRelease(KeyEvent.VK_4);
                break;
            case ClientKeyEvents.KEYCODE_5:
                robot.keyPress(KeyEvent.VK_5);
                robot.keyRelease(KeyEvent.VK_5);
                break;
            case ClientKeyEvents.KEYCODE_6:
                robot.keyPress(KeyEvent.VK_6);
                robot.keyRelease(KeyEvent.VK_6);
                break;
            case ClientKeyEvents.KEYCODE_7:
                robot.keyPress(KeyEvent.VK_7);
                robot.keyRelease(KeyEvent.VK_7);
                break;
            case ClientKeyEvents.KEYCODE_8:
                robot.keyPress(KeyEvent.VK_8);
                robot.keyRelease(KeyEvent.VK_8);
                break;
            case ClientKeyEvents.KEYCODE_9:
                robot.keyPress(KeyEvent.VK_9);
                robot.keyRelease(KeyEvent.VK_9);
                break;
            case ClientKeyEvents.KEYCODE_SPACE:
                robot.keyPress(KeyEvent.VK_SPACE);
                robot.keyRelease(KeyEvent.VK_SPACE);
                break;
            case ClientKeyEvents.KEYCODE_DEL:
                robot.keyPress(KeyEvent.VK_BACK_SPACE);
                robot.keyRelease(KeyEvent.VK_BACK_SPACE);
                break;
            case ClientKeyEvents.KEYCODE_ENTER:
                robot.keyPress(KeyEvent.VK_ENTER);
                robot.keyRelease(KeyEvent.VK_ENTER);
                break;
            case ClientKeyEvents.KEYCODE_SHIFT_LEFT:
                robot.keyPress(KeyEvent.VK_SHIFT);
                robot.keyRelease(KeyEvent.VK_SHIFT);
                break;
        }
    }

    private static void handleActionRelease(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case ActionKey.ACTION_LEFT_MOUSE:
                robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
                break;
            case ActionKey.ACTION_RIGHT_MOUSE:
                robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
                break;
            case ActionKey.ACTION_MOVE_LEFT:
                robot.keyRelease(KeyEvent.VK_LEFT);
                break;
            case ActionKey.ACTION_MOVE_RIGHT:
                robot.keyRelease(KeyEvent.VK_RIGHT);
                break;
            case ActionKey.ACTION_JUMP:
                robot.keyRelease(KeyEvent.VK_SPACE);
                break;
            case "esc":
                robot.keyRelease(KeyEvent.VK_ESCAPE);
                break;
            case ActionKey.ACTION_ENTER:
                robot.keyRelease(KeyEvent.VK_ENTER);
                break;
            case ActionKey.ACTION_SWIPE:
                robot.keyRelease(KeyEvent.VK_CONTROL);
                break;
        }
    }

    private static void handleActionVolumeControl(String execute, String description) {
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
            return;
        }
        switch (description) {
            case "volume_up":
                robot.keyPress(KeyEvent.VK_UP);
                robot.keyRelease(KeyEvent.VK_UP);
                break;
            case "volume_down":
                robot.keyPress(KeyEvent.VK_DOWN);
                robot.keyRelease(KeyEvent.VK_DOWN);
                break;
            case "open_volume_control":
                robot.keyPress(KeyEvent.VK_F9);
                robot.keyRelease(KeyEvent.VK_F9);
                break;
            case "close_volume_control":
                robot.keyPress(KeyEvent.VK_F9);
                robot.keyRelease(KeyEvent.VK_F9);
                break;
        }
    }

    public static void handleAction(String action) {
        JSONObject jsonObject = new JSONObject(action);
        String execute = jsonObject.getString("execute");
        String description = jsonObject.getString("description");
        switch (execute) {
            case "move":
                handleActionMove(execute, description);
                break;
            case "click":
                handleActionClick(execute, description);
                break;
            case "press":
                handleActionPress(execute, description);
                break;
            case "release":
                handleActionRelease(execute, description);
                break;
            case "enter":
                handleActionEnter(execute, description);
                break;
            case "volume_control":
                handleActionVolumeControl(execute, description);
                break;
            case ActionKey.ACTION_EXIT:
                exit();
                break;
        }
    }

    private static void exit() {
        System.exit(0);
    }
}

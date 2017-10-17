package hr.apps.maltar.remotemousecontroller.action;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Maltar on 10.10.2017..
 */

public class Action implements Parcelable {
    private String execute;
    private String description;

    private Action(String execute, String description) {
        this.execute = execute;
        this.description = description;
    }

    public static Action makeMoveAction(int x, int y) {
        return new Action("move", "x=" + x + ":y=" + y);
    }

    public static Action makeClickAction(String button) {
        return new Action("click", button);
    }

    public static Action makePressAction(String button) {
        return new Action("press", button);
    }

    public static Action makeReleaseAction(String button) {
        return new Action("release", button);
    }

    public static Action makeEnterAction(int code) {
        return new Action("enter", String.valueOf(code));
    }

    @Override
    public String toString() {
        return "Action{" +
                "execute='" + execute + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String toJSON() {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("execute", this.execute);
            jsonObject.put("description", this.description);
            return jsonObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "";
        }
    }

    protected Action(Parcel in) {
        execute = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(execute);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Action> CREATOR = new Parcelable.Creator<Action>() {
        @Override
        public Action createFromParcel(Parcel in) {
            return new Action(in);
        }

        @Override
        public Action[] newArray(int size) {
            return new Action[size];
        }
    };
}
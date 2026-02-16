import com.sdmine.recorder.ui.widgets.ClipData;

public class ClipData {

    public String name;
    public int start;
    public int end;
    public boolean keyframe;

    public ClipData(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.keyframe = false;
    }

    public boolean isKeyframe() {
        return keyframe;
    }

    public int length() {
        return end - start;
    }
}

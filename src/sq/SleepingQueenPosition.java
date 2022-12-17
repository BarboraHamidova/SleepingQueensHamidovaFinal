package sq;

import java.util.Objects;

public class SleepingQueenPosition {
    private Integer index;

    public SleepingQueenPosition(Integer index){
        this.index = index;
    }

    Integer getCardIndex (){
        return index;
    }

    public boolean equals(Object o) {
        if (o != null && this.getClass() == o.getClass()) {
            SleepingQueenPosition p = (SleepingQueenPosition)o;
            return this.index == p.getCardIndex();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.index});
    }
}

package max.mustafin.model;

import javax.persistence.*;

@Entity
@Table(name = "history")
public class History {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "data")
    String historyLine;
    public History() {}
    public History(String h) {
        historyLine = h;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHistoryLine() {
        return historyLine;
    }

    public void setHistoryLine(String historyLine) {
        this.historyLine = historyLine;
    }
}

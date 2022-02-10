package Backend;

import Backend.BusinessObjects.Representative;

public class Export {
    private final String title;
    private final String intro;
    private final String chart;
    private final Representative rep;

    private Export(ExportBuilder builder) {
        this.title = builder.title;
        this.intro = builder.intro;
        this.chart = builder.chart;
        this.rep = builder.rep;
    }

    public String getTitle() {
        return title;
    }

    public String getIntro() {
        return intro;
    }

    public String getChart() {
        return chart;
    }

    public Representative getRep() {
        return rep;
    }

    @Override
    public String toString() {
        String exportString = "";
        if(title!=null)
            exportString +=title;
        if(intro!=null )
            exportString +=intro;
        exportString += chart + "\n\n";
        if(rep != null)
        exportString+="Printed by "+rep.toString();
        return exportString;
    }

    public static class ExportBuilder
    {
        private String title;
        private String intro;
        private final String chart;
        private Representative rep;

        public ExportBuilder(String chart){
            this.chart=chart;
        }
        public ExportBuilder title(String title){
            this.title=title;
            return this;
        }
        public ExportBuilder intro(String intro){
            this.intro=intro;
            return this;
        }
        public ExportBuilder rep(Representative rep){
            this.rep=rep;
            return this;
        }
        public Export build(){
            Export export = new Export(this);
            validateExport(export);
            return export;
        }
        private void validateExport(Export export){

        }
    }
}

package pelituotanto;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author helil
 */
public class ajastin implements Kello{
    private java.util.Timer ajastin;
    private long paivitysValiMs;
    
    private List<Tarkkailija> tarkkailijat=new ArrayList<Tarkkailija>();

    public ajastin(int paivitysValiMs) {
        this.paivitysValiMs=paivitysValiMs;
    }
    
    @Override
    public void start() {
        if(ajastin!=null) stop();
        ajastin=new java.util.Timer();
        ajastin.schedule(new Tehtava(),0,paivitysValiMs);
    }

    @Override
    public void stop() {
        ajastin.cancel();
    }

    @Override
    public long getPaivitysvaliMs() {
        return this.paivitysValiMs;
    }

    @Override
    public void setPaivitysvaliMs(int valiMs) {
        this.paivitysValiMs=valiMs;
        start();
    }

    @Override
    public void lisaaTarkkailija(Tarkkailija uusiTarkkailija) {
        tarkkailijat.add(uusiTarkkailija);
    }

    @Override
    public void poistaTarkkailija(Tarkkailija poistettavaTarkkailija) {
        tarkkailijat.remove(poistettavaTarkkailija);
    }
    
    private void ilmoitaTarkkailijoille() {
        for(Tarkkailija t:tarkkailijat) t.paivita();
    }
    
    private class Tehtava extends java.util.TimerTask {
        @Override
        public void run() {
            ilmoitaTarkkailijoille();
        }      
    }
}
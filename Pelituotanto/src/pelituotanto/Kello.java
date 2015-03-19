/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pelituotanto;

/**
 *
 * @author s1200494
 */
public interface Kello {
    void start();
    void stop();
    long getPaivitysvaliMs();
    void setPaivitysvaliMs(int valiMs);
    void lisaaTarkkailija(Tarkkailija uusiTarkkailija);
    void poistaTarkkailija(Tarkkailija poistettavaTarkkailija);
}

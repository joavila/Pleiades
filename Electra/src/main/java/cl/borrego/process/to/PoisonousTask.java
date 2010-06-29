package cl.borrego.process.to;

/**
 * Created by IntelliJ IDEA.
 * User: joavila
 * Date: 01-02-2010
 * Time: 09:50:46 PM
 * Eclipse Public License - v 1.0
 * THE ACCOMPANYING PROGRAM IS PROVIDED UNDER THE TERMS OF THIS ECLIPSE PUBLIC LICENSE ("AGREEMENT").
 * ANY USE, REPRODUCTION OR DISTRIBUTION OF THE PROGRAM CONSTITUTES RECIPIENT’S ACCEPTANCE OF THIS AGREEMENT.
 */
public final class PoisonousTask extends Task {
    public PoisonousTask() {
        super(-1);
    }
    public PoisonousTask(int id) {
        super(-id);
    }
}

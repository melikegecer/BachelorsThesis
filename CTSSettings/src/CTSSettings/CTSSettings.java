package CTSSettings;

/**
 *
 * @author Melike Geçer
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.NbBundle.Messages;

@ActionID(
        category = "Tools",
        id = "com.melike.CTSSettings"
)
@ActionRegistration(
        iconBase = "CTSSettings/settings16.png",
        displayName = "#CTL_CTSSettings"
)
@ActionReference(path = "Toolbars/Build", position = -20)
@Messages("CTL_CTSSettings=CTSSettings")
public final class CTSSettings implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        TextReader.getInstance().read("mqlwksmqlwkmslqskqw");
        CTSSettingsJPanel myPanel = new CTSSettingsJPanel();

        NotifyDescriptor nd = new NotifyDescriptor(
                myPanel, "CTS Settings", NotifyDescriptor.OK_CANCEL_OPTION, NotifyDescriptor.DEFAULT_OPTION,
                null, NotifyDescriptor.OK_CANCEL_OPTION);

        if(DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.CANCEL_OPTION) {
            System.out.println(ColorCodes.PURPLE + "CANCEL" + ColorCodes.RESET);
        } else if(DialogDisplayer.getDefault().notify(nd) == NotifyDescriptor.OK_OPTION) {
            System.out.println(ColorCodes.PURPLE + "OK" + ColorCodes.RESET);
        }
    }
}

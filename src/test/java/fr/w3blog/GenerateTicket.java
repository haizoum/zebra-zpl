/*
 * Copyright 2014.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.w3blog;

import fr.w3blog.zpl.constant.ZebraFont;
import fr.w3blog.zpl.model.ZebraLabel;
import fr.w3blog.zpl.model.ZebraPrintException;
import fr.w3blog.zpl.model.ZebraUtils;
import fr.w3blog.zpl.model.element.ZebraBarCode39;
import fr.w3blog.zpl.model.element.ZebraText;
import fr.w3blog.zpl.utils.ZpltoImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * the goal of this class is generate ticket by using web service 
 * @author Billel LOUBAR loubar_billel@hotmail.com
 */
public class GenerateTicket {

    /**
     *
     * @param args
     * @throws IOException
     * @throws fr.w3blog.zpl.utils.exception.ZebraPrintException
     * @throws ZebraPrintException
     */
    public static void main(String[] args) throws IOException, ZebraPrintException, Exception {
        ZebraLabel zebraLabel = new ZebraLabel(912, 912);
        zebraLabel.setDefaultZebraFont(ZebraFont.ZEBRA_ZERO);

        zebraLabel.addElement(new ZebraText(10, 84, "Product:", 14));
        zebraLabel.addElement(new ZebraText(395, 85, "Camera", 14));

        zebraLabel.addElement(new ZebraText(10, 161, "CA201212AA", 14));

        //Add Code Bar 39
        zebraLabel.addElement(new ZebraBarCode39(10, 297, "CA201212AA", 118, 4, 2));

        zebraLabel.addElement(new ZebraText(10, 365, "Qté:", 11));
        zebraLabel.addElement(new ZebraText(180, 365, "3", 11));
        zebraLabel.addElement(new ZebraText(317, 365, "QA", 11));

        zebraLabel.addElement(new ZebraText(10, 520, "Ref log:", 11));
        zebraLabel.addElement(new ZebraText(180, 520, "0035", 11));
        zebraLabel.addElement(new ZebraText(10, 596, "Ref client:", 11));
        zebraLabel.addElement(new ZebraText(180, 599, "1234", 11));

        zebraLabel.getZplCode();

        File outputfile;

        outputfile = new File("d://zebra_zpl.png");

        ImageIO.write(zebraLabel.getImagePreview(), "png", outputfile);
        
        ZpltoImage.getZplToPicture(zebraLabel, "d://zebra_zpl.png", ZpltoImage.FILE_TYPE.PNG);
        

        System.out.println(zebraLabel.getZplCode());
        

        ZebraUtils.printZpl(zebraLabel, "192.168.50.244", 9100);
    }

}

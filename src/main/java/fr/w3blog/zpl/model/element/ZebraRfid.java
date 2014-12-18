/*
 * Copyright 2014 HP.
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

package fr.w3blog.zpl.model.element;

import fr.w3blog.zpl.model.ZebraElement;

/**
 * Abstract Zebra element to represent a RFID instruction
 * 
 * Command ZPL : All instruction starting ^RF
 * 
 * @author Billel LOUBAR
 * 
 */
public class ZebraRfid  extends ZebraElement{

    /**
     * 
     * @param content 
     */
    public ZebraRfid(String content) {
        
    }
    
    /**
     * 
     * @param id
     * @param content 
     */
    public ZebraRfid(int id, String content){
        
    }
    
    
    

}

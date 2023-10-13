/*
 * Copyright (C) <2017>  <Juan Luis Carrillo Paxtor, Desarrollo JLCP>
 *
 * Copyright (c) GPS Topography y Polygon Area AC son propiedad de Desarrollo JLCP, basada en Quetzaltenango, Guatemala, C.A.  Propiedad de Juan Luis Carrillo Paxtor (JLCP). Se distribuye bajo la licensia AGPL y bajo las siguientes condiciones: 1) tu no distribuiras software en ninguna red sin liberar la totalidad del codigo fuente de tu software bajo la licensia AGPL.  2) debes liberar la totalidad del codigo fuente. 3) Debes liberar cualquier modificacion al codigo de GPS Topography y Polygon Area AC incluyendo modificaciones a las clases Estacion, Poligono y Azimut. 4) Debes mencionar oportunamente a GPS Topography y Desarrollo JLCP e incluir el copyright de GPS Topography  y la licencia AGPL en el metadata.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * __________________________________________________________________________________________________________________________
 * iText Copyright
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU Affero General Public License version 3 as published by the Free Software Foundation with the addition of the following permission added to Section 15 as permitted in Section 7(a): FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY ITEXT GROUP NV, ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT OF THIRD PARTY RIGHTS
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. You should have received a copy of the GNU Affero General Public License along with this program; if not, see http://www.gnu.org/licenses/ or write to the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA, 02110-1301 USA, or download the license from the following URL: http://itextpdf.com/terms-of-use/
 *
 * The interactive user interfaces in modified source and object code versions of this program must display Appropriate Legal Notices, as required under Section 5 of the GNU Affero General Public License.
 *
 * In accordance with Section 7(b) of the GNU Affero General Public License, you must retain the producer line in every PDF that is created or manipulated using iText.
 *
 * You can be released from the requirements of the license by purchasing a commercial license. Buying such a license is mandatory as soon as you develop commercial activities involving the iText software without disclosing the source code of your own applications. These activities include: offering paid services to customers as an ASP, serving PDFs on the fly in a web application, shipping iText with a closed source product.
 * _________________________________________________________________________________________________________________________
 * Copyright Coordinate Conversion by Sami Salkosuo
 * https://www.ibm.com/developerworks/library/j-coordconvert/index.html
 *
 * Downloader Agreement
 * The following are terms of a legal downloader agreement (the "Agreement") regarding Your download of Content (as defined below) from this Website. IBM may change these terms of use and other requirements and guidelines for use of this Website at its sole discretion. This Website may contain other proprietary notices and copyright information the terms of which must be observed and followed. Any use of the Content in violation of this Agreement is strictly prohibited.
 * "Content" includes, but is not limited to, software, text and/or speech files, code, associated materials, media and /or documentation that You download from this Website. The Content may be provided by IBM or third-parties. IBM Content is owned by IBM and is copyrighted and licensed, not sold. Third-party Content is owned by its respective owner and is licensed by the third party directly to You. IBM's decision to permit posting of third-party Content does not constitute an express or implied license from IBM to You or a recommendation or endorsement by IBM of any particular product, service, company or technology.
 * The party providing the Content (the "Provider") grants You a nonexclusive, worldwide, irrevocable, royalty-free, copyright license to edit, copy, reproduce, publish, publicly display and/or perform, format, modify and/or make derivative works of, translate, re-arrange, and distribute the Content or any portions thereof and to sublicense any or all such rights and to permit sublicensees to further sublicense such rights, for both commercial and non-commercial use, provided You abide by the terms of this Agreement. You understand that no assurances are provided that the Content does not infringe the intellectual property rights of any other entity. Neither IBM nor the provider of the Content grants a patent license of any kind, whether expressed or implied or by estoppel. As a condition of exercising the rights and licenses granted under this Agreement, You assume sole responsibility to obtain any other intellectual property rights needed.
 * The Provider of the Content is the party that submitted the Content for Posting and who represents and warrants that they own all of the Content, (or have obtained all written releases, authorizations and licenses from any other owner(s) necessary to grant IBM and downloaders this license with respect to portions of the Content not owned by the Provider). All information provided on or through this Website may be changed or updated without notice. You understand that IBM has no obligation to check information and /or Content on the Website and that the information and/or Content provided on this Web site may contain technical inaccuracies or typographical errors.
 * IBM may, in its sole discretion, discontinue the Website, any service provided on or through the Website, as well as limit or discontinue access to any Content posted on the Website for any reason without notice. IBM may terminate this Agreement and Your rights to access, use and download Content from the Website at any time, with or without cause, immediately and without notice.
 * ALL INFORMATION AND CONTENT IS PROVIDED ON AN "AS IS" BASIS. IBM MAKES NO REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED, CONCERNING USE OF THE WEBSITE, THE CONTENT, OR THE COMPLETENESS OR ACCURACY OF THE CONTENT OR INFORMATION OBTAINED FROM THE WEBSITE. IBM SPECIFICALLY DISCLAIMS ALL WARRANTIES WITH REGARD TO THE IMPLIED WARRANTIES OF NON-INFRINGEMENT, MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. IBM DOES NOT WARRANT UNINTERRUPTED OR ERROR-FREE OPERATION OF ANY CONTENT. IBM IS NOT RESPONSIBLE FOR THE RESULTS OBTAINED FROM THE USE OF THE CONTENT OR INFORMATION OBTAINED FROM THE WEBSITE.
 * LIMITATION OF LIABILITY. IN NO EVENT WILL IBM BE LIABLE TO ANY PARTY FOR ANY DIRECT, INDIRECT, SPECIAL OR OTHER CONSEQUENTIAL DAMAGES FOR ANY USE OF THIS WEBSITE, THE USE OF CONTENT FROM THIS WEBSITE, OR ON ANY OTHER HYPER LINKED WEB SITE, INCLUDING, WITHOUT LIMITATION, ANY LOST PROFITS, BUSINESS INTERRUPTION, LOSS OF PROGRAMS OR OTHER DATA ON YOUR INFORMATION HANDLING SYSTEM OR OTHERWISE, EVEN IF IBM IS EXPRESSLY ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 * The laws of the State of New York, USA govern this Agreement, without reference to conflict of law principles. The "United Nations Convention on International Sale of Goods" does not apply. This Agreement may not be assigned by You. The parties agree to waive their right to a trial by jury.
 * This Agreement is the complete and exclusive agreement between the parties and supersedes all prior agreements, oral or written, and all other communications relating to the subject matter hereof. For clarification, it is understood and You agree, that any additional agreement or license terms that may accompany the Content is invalid, void, and non-enforceable to any downloader of this Content including IBM.
 * If any section of this Agreement is found by competent authority to be invalid, illegal or unenforceable in any respect for any reason, the validity, legality and enforceability of any such section in every other respect and the remainder of this Agreement shall continue in effect.
 * __________________________________________________________________________________________________________________________
 * Copyright ANDROID ADXF LIBRARY by Jonathan Sevy
 *
 * MIT License
 *
 * Copyright (c) [year] [fullname]
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package com.desarrollojlcp.gps_topography.map.model;

import java.io.Serializable;
import java.util.Vector;

/**
 * Created by LoCo5 on 27/05/2017.
 */

public class Estacion extends Azimut implements Cloneable, Serializable {
    public int getIdEstacion() {
        return idEstacion;
    }

    public void setIdEstacion(int idEstacion) {
        this.idEstacion = idEstacion;
    }

    public String getIdEst() {
        return idEst;
    }

    public void setIdEst(String idEst) {
        this.idEst = idEst;
    }

    public double getAlt() {
        return alt;
    }

    public void setAlt(double alt) {
        this.alt = alt;
    }

    int idEstacion;
    public double Norte = 0;
    public double Sur = 0;
    public double Este = 0;
    public double Oeste = 0;
    public double xp = 0;
    public double yp = 0;
    public double nc = 0;
    public double sc = 0;
    public double ec = 0;
    public double oc = 0;
    public double ypc = 0;
    public double xpc = 0;
    public double xt = 0;
    public double yt = 0;
    double zt = 0;
    double xi = 0;
    double yi = 0;
    public double YX = 0;
    public double XY = 0;
    double cota = 0;
    public double dist = 0;
    Azimut azimut;
    public String idEst;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    double lon = 0;
    double lat = 0;
    double alt = 0;
    public String UTM = "";
    double easting = 0;
    double northing = 0;
    Integer zone = 0;
    String latZone = "";
    String hemisphere = "";
    public boolean ultimoPunto = false;
    int contadorRadiaciones = 0;
    String tipoMedicion = "";
    public Vector<Estacion> radiaciones = new Vector<Estacion>();

    public boolean isPartePoligonoFinal() {
        return partePoligonoFinal;
    }

    public void setPartePoligonoFinal(boolean partePoligonoFinal) {
        this.partePoligonoFinal = partePoligonoFinal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    boolean partePoligonoFinal = false;
    protected String idAnterior = "";
    public int id_bd = 0;
    protected String observaciones = "";


    public Estacion(){

    }

    public Estacion(double lati, double longi){
        lat = lati;
        lon = longi;
    }

    public Estacion(float distancia, Azimut azimut1){
        dist = distancia;
        azimut = azimut1;
    }

    public Vector<Estacion> getRadiaciones(){
        return this.radiaciones;
    }



    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

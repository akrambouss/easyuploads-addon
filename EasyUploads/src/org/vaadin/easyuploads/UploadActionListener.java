/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Class org.vaadin.easyuploads.UploadActionListener.java created on 04.02.2013 by steffen.c-on
 *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *   *
 * (C) Copyright by c-on Services Steffen Ewert 2013; All rights reserved.
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */

package org.vaadin.easyuploads;

/** Upload action listener interface which can be used for informing about upload actions.
 * @author steffen.c-on
 * @version 04.02.2013
 */
public interface UploadActionListener {

  /** Called if a upload of a file is started.
   * @param filename name of the file which is currently uploading
   * @param pendingFiles number of pending files (without the currently uploading file)
   */
  void fileUploadStarted(String filename, int pendingFiles);
  
  /** Called if a upload of a file is finished successful.
   * @param filename name of the file which is currently finished
   * @param pendingFiles number of pending files
   */
  void fileUploadFinished(String filename, int pendingFiles);
  
  /** Called if a upload of a file is finished with an error.
   * @param filename name of the file which is aborted
   * @param pendingFiles number of pending files
   */
  void fileUploadError(String filename, int pendingFiles);
  
}


// (C) Copyright by c-on Services Steffen Ewert 2013; All rights reserved.
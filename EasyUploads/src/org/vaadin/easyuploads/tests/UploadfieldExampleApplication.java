package org.vaadin.easyuploads.tests;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.vaadin.easyuploads.FileBuffer;
import org.vaadin.easyuploads.FileFactory;
import org.vaadin.easyuploads.MultiFileUpload;
import org.vaadin.easyuploads.UploadField;
import org.vaadin.easyuploads.UploadField.FieldType;
import org.vaadin.easyuploads.UploadField.StorageMode;

import com.vaadin.Application;
import com.vaadin.data.util.ObjectProperty;
import com.vaadin.terminal.Resource;
import com.vaadin.terminal.StreamResource;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

@SuppressWarnings("serial")
public class UploadfieldExampleApplication extends Application {

    @Override
    public void init() {
        final Window mainWindow = new Window("Easy upload tests");
        setMainWindow(mainWindow);

        final UploadField uploadField = new UploadField();
        uploadField.setCaption("Default mode: temp files, fieldType:"
                + uploadField.getFieldType());

        Button b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(uploadField);
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        final UploadField uploadField2 = new UploadField();
        uploadField2.setFieldType(FieldType.FILE);
        uploadField2.setCaption("Storagemode: temp files, fieldType:"
                + uploadField2.getFieldType());

        b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField2.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(uploadField2);
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        final UploadField uploadField_nonimmediate = new UploadField();
        uploadField_nonimmediate.setFieldType(FieldType.FILE);
        uploadField_nonimmediate.setImmediate(false);
        uploadField_nonimmediate
                .setCaption("Non immediate, Storagemode: temp files, fieldType:"
                        + uploadField_nonimmediate.getFieldType());

        b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField_nonimmediate.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        Button submit = new Button("Send file");
        submit.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                uploadField_nonimmediate.submitUpload();
            }
        });
        mainWindow.addComponent(uploadField_nonimmediate);
        mainWindow.addComponent(submit);
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        final UploadField uploadField3 = new UploadField();
        uploadField3.setFieldType(FieldType.FILE);
        uploadField3.setCaption("Storagemode: /Users/Shared/tmp/ , fieldType:"
                + uploadField3.getFieldType());

        uploadField3.setFileFactory(new FileFactory() {
            public File createFile(String fileName, String mimeType) {
                File f = new File("/Users/Shared/tmp/" + fileName);
                return f;
            }
        });

        b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField3.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(uploadField3);
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        final UploadField uploadField4 = new UploadField();
        uploadField4.setStorageMode(StorageMode.MEMORY);
        uploadField4.setFieldType(FieldType.UTF8_STRING);
        uploadField4
                .setCaption("writethrough=false, Storagemode: memory , fieldType:"
                        + uploadField4.getFieldType());

        uploadField4.setPropertyDataSource(new ObjectProperty<String>(
                "propertyvalue"));
        uploadField4.setWriteThrough(false);
        mainWindow.addComponent(uploadField4);

        b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField4.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(b);
        b = new Button("Discard");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                uploadField4.discard();
                Object value = uploadField4.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        final UploadField uploadField5 = new UploadField();
        uploadField5.setFieldType(FieldType.BYTE_ARRAY);
        uploadField5.setCaption("Storagemode: memory , fieldType:"
                + uploadField5.getFieldType());

        b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField5.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(uploadField5);
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        final UploadField uploadField6 = new UploadField() {
            @Override
            protected void updateDisplay() {
                final byte[] pngData = (byte[]) getValue();
                String filename = getLastFileName();
                String mimeType = getLastMimeType();
                long filesize = getLastFileSize();
                if (mimeType.equals("image/png")) {
                    Resource resource = new StreamResource(
                            new StreamResource.StreamSource() {
                                public InputStream getStream() {
                                    return new ByteArrayInputStream(pngData);
                                }
                            }, "", getApplication()) {
                        @Override
                        public String getMIMEType() {
                            return "image/png";
                        }
                    };
                    Embedded embedded = new Embedded("Image:" + filename + "("
                            + filesize + " bytes)", resource);
                    getRootLayout().addComponent(embedded);
                } else {
                    super.updateDisplay();
                }
            }
        };
        uploadField6.setFieldType(FieldType.BYTE_ARRAY);
        uploadField6
                .setCaption("Storagemode: memory , fieldType:"
                        + uploadField6.getFieldType()
                        + ", overridden methods to display possibly loaded PNG in preview.");

        b = new Button("Show value");
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                Object value = uploadField6.getValue();
                mainWindow.showNotification("Value:" + value);
            }
        });
        mainWindow.addComponent(uploadField6);
        mainWindow.addComponent(b);
        mainWindow.addComponent(hr());

        MultiFileUpload multiFileUpload = new MultiFileUpload() {
            @Override
            protected void handleFile(File file, String fileName,
                    String mimeType, long length) {
                String msg = fileName + " uploaded. Saved to temp file "
                        + file.getAbsolutePath() + " (size " + length
                        + " bytes)";
                mainWindow.showNotification(msg);
            }
        };
        multiFileUpload.setCaption("MultiFileUpload");
        mainWindow.addComponent(multiFileUpload);
        mainWindow.addComponent(hr());

        MultiFileUpload multiFileUpload2 = new MultiFileUpload() {
            @Override
            protected void handleFile(File file, String fileName,
                    String mimeType, long length) {
                String msg = fileName + " uploaded. Saved to file "
                        + file.getAbsolutePath() + " (size " + length
                        + " bytes)";

                mainWindow.showNotification(msg);
            }

            @Override
            protected FileBuffer createReceiver() {
                FileBuffer receiver = super.createReceiver();
                /*
                 * Make receiver not to delete files after they have been
                 * handled by #handleFile().
                 */
                receiver.setDeleteFiles(false);
                return receiver;
            }
        };
        multiFileUpload2.setCaption("MultiFileUpload (with root dir)");
        multiFileUpload2.setRootDirectory("/Users/Shared/tmp/");
        mainWindow.addComponent(multiFileUpload2);

        mainWindow.addComponent(hr());
        MultiFileUpload multiFileUpload3 = new SlowMultiFileUpload();
        multiFileUpload3.setCaption("MultiFileUpload (simulated slow network)");
        multiFileUpload3.setUploadButtonCaption("Choose File(s)");
        mainWindow.addComponent(multiFileUpload3);

    }

    class SlowMultiFileUpload extends MultiFileUpload {
        @Override
        protected void handleFile(File file, String fileName, String mimeType,
                long length) {
            String msg = fileName + " uploaded.";
            getWindow().showNotification(msg);
        }

        @Override
        protected FileBuffer createReceiver() {
            return new FileBuffer() {
                @Override
                public FileFactory getFileFactory() {
                    return SlowMultiFileUpload.this.getFileFactory();
                }

                @Override
                public OutputStream receiveUpload(String filename,
                        String MIMEType) {
                    final OutputStream receiveUpload = super.receiveUpload(
                            filename, MIMEType);
                    OutputStream slow = new OutputStream() {
                        private int slept;
                        private int written;

                        @Override
                        public void write(int b) throws IOException {
                            receiveUpload.write(b);
                            written++;
                            if (slept < 60000 && written % 1024 == 0) {
                                int sleep = 5;
                                slept += sleep;
                                try {
                                    Thread.sleep(sleep);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                            }
                        }
                    };
                    return slow;
                }

            };
        }

    }

    private Component hr() {
        Label label = new Label("<hr>", Label.CONTENT_XHTML);
        return label;
    }

}

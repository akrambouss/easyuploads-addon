package org.vaadin.easyuploads;

import java.io.OutputStream;
import java.util.Collection;

import org.vaadin.easyuploads.MultiUpload.FileDetail;

import com.vaadin.terminal.StreamVariable.StreamingEndEvent;
import com.vaadin.terminal.StreamVariable.StreamingErrorEvent;
import com.vaadin.terminal.StreamVariable.StreamingProgressEvent;
import com.vaadin.terminal.StreamVariable.StreamingStartEvent;

public interface MultiUploadHandler {

	void streamingStarted(StreamingStartEvent event);

	void streamingFinished(StreamingEndEvent event);

	OutputStream getOutputStream();

	void streamingFailed(StreamingErrorEvent event);

	void onProgress(StreamingProgressEvent event);

	void filesQueued(Collection<FileDetail> pendingFileNames);

}

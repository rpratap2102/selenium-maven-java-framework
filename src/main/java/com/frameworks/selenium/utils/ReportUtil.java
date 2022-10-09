package com.frameworks.selenium.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frameworks.selenium.exceptions.FrameworkException;

public class ReportUtil {

	private static final Logger log = LoggerFactory.getLogger(ReportUtil.class);
	private static final String BaseDir = System.getProperty("user.dir");
	public static final String FILEPATH = BaseDir + Constants.REPORT_PATH + "/" + getCurrentDateTimeAsString();

	private static String getCurrentDateTimeAsString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.TIMESTAMP_FORMAT);
		Date now = new Date();
		String uniqueText = dateFormat.format(now);
		return (uniqueText);
	}

	private static void archiveReport() {
		log.info("Archiving old reports");
		StringBuilder archiveFile = new StringBuilder(ReportUtil.getCurrentDateTimeAsString());
		Path archiveDir = Paths.get(BaseDir).resolve(Constants.ARCHIVE_FOLDER_NAME);
		Path reportBaseDir = Paths.get(BaseDir + Constants.REPORT_PATH);
		Path archivePath = archiveDir.resolve(archiveFile.append(".zip").toString().replace(':', '_'));

		archivePath.getParent().toFile().mkdirs();

		try {
			zipFolder(reportBaseDir, archivePath);
			log.info("Archive generated at : {}", archivePath);
		} catch (Exception archiveException) {
			log.error("Error in creating zip folder for archive due to: {}", archiveException.getMessage());
		}
	}

	private static void deleteDirectory(String folder) {
		log.info("Deleting folder: {}", folder);
		try {
			FileUtils.deleteDirectory(new File(folder));
		} catch (IOException e) {
			log.error("Error in deleteing folder :{}", folder);
			throw new FrameworkException("Errorn in deleting folder", e);
		}
	}

	public static void cleanOldReport() {
		archiveReport();
		deleteDirectory(Paths.get(BaseDir).resolve(Constants.REPORT_FOLDER_NAME).toString());
		deleteDirectory(Paths.get(BaseDir).resolve("test-output").toString());

	}

	private static void zipFolder(Path dataPath, Path zipPath) throws IOException {
		ZipOutputStream zipOutput = new ZipOutputStream(new FileOutputStream(zipPath.toFile()));
		try {
			Files.walkFileTree(dataPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attributes) throws IOException {
					zipOutput.putNextEntry(new ZipEntry(dataPath.relativize(file).toString()));
					Files.copy(file, zipOutput);
					zipOutput.closeEntry();
					return FileVisitResult.CONTINUE;
				}
			});
		} finally {
			zipOutput.close();
		}
	}
}

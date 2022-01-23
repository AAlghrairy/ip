package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    protected File dukeFile;
    protected File dukeFolder;

    public Storage(String filePath) {
        String folderPath = filePath.substring(0, filePath.indexOf("/"));
        this.dukeFile = new File(filePath);
        this.dukeFolder = new File(folderPath);
    }

    public List<Task> load() throws DukeException, IOException {
        List<Task> tasks = new ArrayList<>();
        try {
            Scanner s = new Scanner(dukeFile);

            while (s.hasNext()) {
                String currLine = s.nextLine();
                String[] currLineArr = currLine.split("\\|");

                switch (currLineArr[0]) {
                case "T":
                    Task tempT = new ToDo(currLineArr[2]);

                    if (currLineArr[1].equals("X")) {
                        tempT.markDone();
                    }

                    tasks.add(tempT);
                    break;
                case "D":
                    LocalDate tempDate = LocalDate.parse(currLineArr[3], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime tempTime = currLineArr.length < 5 ? null : LocalTime.parse(currLineArr[4],
                            DateTimeFormatter.ofPattern("HH:mm"));

                    Task tempD = tempTime == null ? new Deadline(currLineArr[2], tempDate) : new Deadline(
                            currLineArr[2], tempDate, tempTime);

                    if (currLineArr[1].equals("X")) {
                        tempD.markDone();
                    }

                    tasks.add(tempD);
                    break;
                case "E":
                    LocalDate tempDateEvent = LocalDate.parse(currLineArr[3],
                            DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    LocalTime tempTimeBegin = LocalTime.parse(currLineArr[4], DateTimeFormatter.ofPattern("HH:mm"));
                    LocalTime tempTimeEnd = LocalTime.parse(currLineArr[5], DateTimeFormatter.ofPattern("HH:mm"));
                    Task tempE = new Event(currLineArr[2], tempDateEvent, tempTimeBegin, tempTimeEnd);

                    if (currLineArr[1].equals("X")) {
                        tempE.markDone();
                    }

                    tasks.add(tempE);
                    break;
                default:
                    //empty
                }
            }
        } catch (FileNotFoundException e) {
            if (!dukeFolder.exists()) {
                dukeFolder.mkdirs();
            }

            if (!dukeFile.exists()) {
                dukeFile.createNewFile();
            }

            throw new DukeException("Pardon me! But the file was not found");
        }

        return tasks;
    }

    public void modifyStorage(Task task, ConfirmCodes code, TaskList tasks) throws DukeException {
        try {
            FileWriter fw;
            switch (code) {
            case ADDITION:
                fw = new FileWriter(dukeFile.getPath(), true);
                fw.write(task.toStringInFileFormat() + System.lineSeparator());
                fw.close();
                break;
            case DELETION:
                fw = new FileWriter(dukeFile.getPath());
                fw.write(listInFileFormat(tasks));
                fw.close();
                break;
            default:
                throw new DukeException("INTERNAL ERROR: Invalid Type Declaration");
            }
        } catch (IOException e) {
            throw new DukeException("INTERNAL ERROR: File cannot be accessed. Check directory.");
        }
    }

    private String listInFileFormat(TaskList taskList) {
        List<Task> tasks = taskList.getList();
        StringBuilder list = new StringBuilder();

        for (Task t : tasks) {
            list.append(t.toStringInFileFormat()).append(System.lineSeparator());
        }

        return list.toString();
    }
}

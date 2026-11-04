import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab9
{
    public static void main(String[] args)
    {
        Scanner myScan = new Scanner(System.in);

        System.out.println("[Diff Detector]");
        System.out.print("Enter the name of the file 1: ");
        String file1 = myScan.nextLine();

        System.out.print("Enter the name of the file 2: ");
        String file2 = myScan.nextLine();

        File fileName1 = new File(file1);
        File fileName2 = new File(file2);

        System.out.println();
        System.out.println(diff(fileName1, fileName2));
        System.out.println();
        System.out.println("Program complete");

        myScan.close();
    }
    public static String diff(File file1, File file2)
    {
        String result = "";
        boolean filesAreIdentical = true;
        Scanner scanner1 = null;
        Scanner scanner2 = null;

        try
        {
            scanner1 = new Scanner(file1);
            scanner2 = new Scanner(file2);

            int lineNum = 1;

            while (scanner1.hasNextLine() || scanner2.hasNextLine()) {
                String line1;
                String line2;

                if (scanner1.hasNextLine())
                {
                    line1 = scanner1.nextLine();
                }
                else
                {
                    line1 = null;
                }

                if (scanner2.hasNextLine())
                {
                    line2 = scanner2.nextLine();
                }
                else
                {
                    line2 = null;
                }

                if (line1 == null || line2 == null)
                {
                    result = result + "Files have different number of lines\n";
                    filesAreIdentical = false;
                    break;
                }

                if (!line1.equals(line2))
                {
                    result = result + "Line " + lineNum + ":\n";
                    result = result + "< " + line1 + "\n";
                    result = result + "> " + line2 + "\n";
                    filesAreIdentical = false;
                }

                lineNum++;
            }

            if (filesAreIdentical)
            {
                result = "Files are identical";
            }

        }
        catch (FileNotFoundException e)
        {
            if (!file1.exists())
            {
                result = file1.getName() + " (The system cannot find the file specified)";
            }
            else
            {
                result = file2.getName() + " (The system cannot find the file specified)";
            }
        }
        finally
        {
            if (scanner1 != null)
            {
                scanner1.close();
            }
            if (scanner2 != null)
            {
                scanner2.close();
            }
        }
        return result;
    }
}

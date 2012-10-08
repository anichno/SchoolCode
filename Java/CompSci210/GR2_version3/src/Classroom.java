
/**
 * GR 2: Problem 7          Limit your time to approximately 15 minutes
 *
 * Grade:           / 21
 */
public class Classroom {

  private String roomNum = null;
  private int roomSize = 0;

  public Classroom() {
    roomNum = "4F3";
    roomSize = 200;
  }

  public Classroom(String roomNum, int roomSize) {
    this.roomNum = roomNum;
    this.roomSize = roomSize;
  }

  public void setClassroom(String roomNum) {
    this.roomNum = roomNum;
  }

  public String getClassroom() {
    String room = this.roomNum;
    return room;
  }

  public int getRoomSize() {
    return roomSize;
  }

  public void setRoomSize(int roomSize) {
    this.roomSize = roomSize;
  }
}

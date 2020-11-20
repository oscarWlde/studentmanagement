package com.example.demo.util.classroom;

import com.example.demo.bean.Classroom;
import com.example.demo.bean.Take;

import java.util.List;

public class room_state {


        public String name;
        public String state_1[] = new String[49];
        public String information[] = new String[49];
        int week = 0;
        int day = 0;
        int exact = 0;
        public room_state(String classroomid)
        {
            this.name=classroomid;
            for(int i = 0;i<49;i++)
            {
                state_1[i]="0";
                information[i]=" ";
            }
        }

        public void change_state(List<Take> a)
        {
            System.out.println(name);
            for(int i = 0;i<a.size();i++)
            {
                System.out.println("classroom"+a.get(i).getClassroomId());
                if(name.equals(a.get(i).getClassroomId()))
                {
                    week = Integer.parseInt(a.get(i).getWeek());
                    day = Integer.parseInt(a.get(i).getDay());
                    exact =(day-1)*7;
                    for(int j = Integer.parseInt(a.get(i).getStartTime());j<=Integer.parseInt(a.get(i).getEndTime());j++)
                    {
                        state_1[exact+j-1] = a.get(i).getType();
                        information[exact+j-1] = a.get(i).getInformation();
                    }

                    System.out.println("infromation   "+information[exact]);
                }
            }
        }


}

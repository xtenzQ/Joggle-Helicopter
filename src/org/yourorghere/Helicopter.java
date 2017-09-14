/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;

/**
 * ����� ��������
 * @author RichardKelly
 */
public class Helicopter {

    /**
     * �������������� ��-28 (��������� ��������� ��� ���������������� ������)
     * �������� ���������:
     * 1. ����������� �����: 1500 �.�.
     * 2. �������� �����: 2200 �.�. (100%)
     * 3. �������� �������: 700 �.�. (0%)
     * ������� �������� �����: 240 ��/��� (8*PI ��� FPS = 60)
     * ������� �������� �����: 1200 ��/��� (40*PI ��� FPS = 60)
     * �����: 11 ����
     * 
     * ����:
     * 0% - 220 ��/��� - 0 ��
     * 50% - 240 ��/��� - 11 000 ��
     * 100% - 260 ��/���
     * 
     * ���� ������ �� ������� � �� ��������� (�������). ��������, ��� 50% ������������� 11 ����.
     */
    
    // ������ ��������
    private final Object body;
    // ������ �������� �����
    private final Object mainScrew;
    // ������ �������� �����
    private final Object rollScrew;

    private static final double toRadian = Math.PI / 180;
    private static final double toDegree = 180 / Math.PI;
    
    // FPS
    public final int FPS = 60;
    
    public Vector3 position;
    public Vector3 angles;

    // ���������� ��������� ���������
 
    // �����
    public final float mass = 11000; // 11 000 ��
    // ������������ �������� ������������ ��������
    public final float maxSpeedY = 20; // 20 �/�
    
    // ��������
    public Vector3 speed;
    // ���������
    public Vector3 accel;

    // ������� ��������
    public Vector3 speedAngle;
    // ������� ���������
    public Vector3 accelAngle;
    
    // ����
    public static float pull = (float) 0;
    // �������� �������� ������������ ��� ��������
    public static float pullAngle = (float) 0.3;

    public Helicopter() {
        body = new Object("heli/Helicopter.obj");
        mainScrew = new Object("heli/MainScrew.obj");
        /* Cinema 4D fix (according to coordinades) */
        // ���� �������� �����
        mainScrew.position.x = (float) 0.05961;
        mainScrew.position.y = (float) 3.58183;
        mainScrew.position.z = (float) -0.07208;
        mainScrew.angles.x = (float) -3;

        rollScrew = new Object("heli/RollScrew.obj");
        // ���� �������� �����
        rollScrew.position.x = (float) -0.51373;
        rollScrew.position.y = (float) 3.06563;
        rollScrew.position.z = (float) -10.9899;

        Texture texture = TextureLoader.load("heli/texture.jpg");
        body.setTexture(texture);
        mainScrew.setTexture(texture);
        rollScrew.setTexture(texture);

        position = new Vector3(0, 0, 0);
        angles = new Vector3(0, 0, 0);

        speed = new Vector3(0, 0, 0);
        accel = new Vector3(0, 0, 0);

        speedAngle = new Vector3(0, 0, 0);
        accelAngle = new Vector3(0, 0, 0);
    }

    /**
     * ���������� �����������
     */
    public void physImpact() {
        /* ��������� �� ����������� */
        position.add(speed);
        speed.add(accel);
        // 0.1633 = 9.8 �/�2 / 60 (FPS)
        speed.y -= 9.8 / FPS * 0.21;
        // �������������
        speed.mult(0.95);
        // ��������� �� ����
        angles.add(speedAngle);
        speedAngle.add(accelAngle);
        speedAngle.mult(0.7);

        mainScrew.angles.y += 8 * Math.PI ;//5 ------ 8*Pi
        mainScrew.angles.y += (speed.y > 0) ? 3 : -3; //3

        rollScrew.angles.x += 40 * Math.PI;//10 ------ 40*Pi
        rollScrew.angles.x += (speedAngle.y > 0) ? 3 : -3; //3
        rollScrew.angles.x += (speed.y > 0) ? 2 : -2; //2
    }

    /**
     * �������� �������� � ������������
     * @param height ������
     */
    public void collision(float height) {
        if (height > position.y - 0.3656) {
            position.y += height - position.y + 0.3656;
            speed.y = 0;
        }
    }

    /**
     * ���������
     * @param gl 
     */
    public void draw(GL gl) {
        gl.glPushMatrix();
        gl.glTranslatef(position.x, position.y, position.z);
        gl.glRotated(angles.y, 0, 1, 0);
        gl.glRotated(angles.x, 1, 0, 0);
        gl.glRotated(angles.z, 0, 0, 1);

        body.draw(gl);
        mainScrew.draw(gl);
        rollScrew.draw(gl);

        gl.glPopMatrix();
    }

}

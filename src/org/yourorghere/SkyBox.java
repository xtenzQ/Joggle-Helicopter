/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.yourorghere;

import com.sun.opengl.util.texture.Texture;
import javax.media.opengl.GL;

/**
 *
 * @author RichardKelly
 */
public class SkyBox {
        
    public Texture texture;
    
    public float radius;
    
    public SkyBox(Texture texture) {
        
        this.texture = texture;
        radius = 10;
    }
    
    /**
     * ���������� SkyBox
     * @param gl �������� ���������
     */
    public void draw(GL gl) {

        gl.glColor3d(1, 1, 1);
        gl.glBindTexture(GL.GL_TEXTURE_2D, texture.getTextureObject());
        gl.glBegin(GL.GL_QUADS);
        /*
        // ����� ����� (������������� x)
        gl.glVertex3f(x[1], y[1], z[1]);
        gl.glVertex3f(x[2], y[2], z[2]);
        gl.glVertex3f(x[5], y[5], z[5]);
        gl.glVertex3f(x[6], y[6], z[6]);
        // �������� ����� (������������� z)
        gl.glVertex3f(x[2], y[2], z[2]);
        gl.glVertex3f(x[3], y[3], z[3]);
        gl.glVertex3f(x[6], y[6], z[6]);
        gl.glVertex3f(x[7], y[7], z[7]);
        // ������ ����� (������������� x)
        gl.glVertex3f(x[1], y[1], z[1]);
        gl.glVertex3f(x[2], y[2], z[2]);
        gl.glVertex3f(x[5], y[5], z[5]);
        gl.glVertex3f(x[6], y[6], z[6]);
        */
        // ����� �����
        gl.glTexCoord2f(.2505f, .334f);
        gl.glVertex3f(-radius, radius, -radius);        
        gl.glTexCoord2f(0, .334f);
        gl.glVertex3f(-radius, radius, radius);     
        gl.glTexCoord2f(0, .666f);      
        gl.glVertex3f(-radius, -radius, radius);      
        gl.glTexCoord2f(.2505f, .666f);
        gl.glVertex3f(-radius, -radius, -radius);
        // ������ �����
        gl.glTexCoord2f(.75f, .334f);
        gl.glVertex3f(radius, radius, radius);
        gl.glTexCoord2f(.4996f, .334f);
        gl.glVertex3f(radius, radius, -radius);        
        gl.glTexCoord2f(.4996f, .666f);
        gl.glVertex3f(radius, -radius, -radius);       
        gl.glTexCoord2f(.75f, .666f);
        gl.glVertex3f(radius, -radius, radius);
        // ������� �����
        gl.glTexCoord2f(.4996f, .334f);
        gl.glVertex3f(radius, radius, -radius);
        gl.glTexCoord2f(.2505f, .334f);
        gl.glVertex3f(-radius, radius, -radius);   
        gl.glTexCoord2f(.2505f, .666f);
        gl.glVertex3f(-radius, -radius, -radius);       
        gl.glTexCoord2f(.4996f, .666f);
        gl.glVertex3f(radius, -radius, -radius);
        // ������ �����
        gl.glTexCoord2f(1.0f, .334f);
        gl.glVertex3f(-radius, radius, radius);
        gl.glTexCoord2f(.75f, .334f);
        gl.glVertex3f(radius, radius, radius);
        gl.glTexCoord2f(.75f, .666f);
        gl.glVertex3f(radius, -radius, radius);
        gl.glTexCoord2f(1.0f, .666f);
        gl.glVertex3f(-radius, -radius, radius);
        // ������� �����
        gl.glTexCoord2f(.2505f, 0.334f);
        gl.glVertex3f(-radius, radius, -radius);
        gl.glTexCoord2f(.4996f, 0.334f);
        gl.glVertex3f(radius, radius, -radius);
        gl.glTexCoord2f(.4996f, 0f);
        gl.glVertex3f(radius, radius, radius);
        gl.glTexCoord2f(.2505f, 0f);
        gl.glVertex3f(-radius, radius, radius);
        // ������ �����
        gl.glTexCoord2f(.2505f, 1.0f);
        gl.glVertex3f(-radius, -radius, radius);
        gl.glTexCoord2f(.4996f, 1.0f);
        gl.glVertex3f(radius, -radius, radius);
        gl.glTexCoord2f(.4996f, 0.666f);
        gl.glVertex3f(radius, -radius, -radius);
        gl.glTexCoord2f(.2505f, 0.666f);
        gl.glVertex3f(-radius, -radius, -radius);
        gl.glEnd();
    }
    
}

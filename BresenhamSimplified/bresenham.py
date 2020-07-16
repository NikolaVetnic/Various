#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Wed Apr  1 23:40:52 2020

@author: nikolavetnic
"""


PX_OFF_ALT  = '□'
PX_OFF      = ' '
PX_ON       = '■' 


class Pixel:
    
    def __init__(self, x, y):
        self.x = x
        self.y = y


class Screen:    
    
    def __init__(self, size):
        self.size = size
        self.area = size**2 * PX_OFF
    
    def print_screen(self):
        print()
        for row in range(self.size - 1, -1, -1):
            print('{:02d} |'.format(row+1), ' '.join(self.area[row * self.size : (row + 1) * self.size]))
        print()
        
    def draw_pixel(self, Pixel):
        index = self.size * (Pixel.y - 1) + (Pixel.x - 1)
        self.area = self.area[:index] + PX_ON + self.area[index+1:]
        
    def draw_line(self, A, B):
        
        # A uvek mora biti levo od B
        if A.x > B.x:
            T = A
            A = B
            B = T
        
        # iscrtavanje krajnjih piksela
        self.draw_pixel(A)
        self.draw_pixel(B)
        
        # racunanje udaljenosti tacaka A i B kao uredjene dvojke u zavisnosti od njihovog polozaja
        D = Pixel(B.x - A.x, A.y - B.y) if A.y <= B.y else Pixel(B.x - A.x, B.y - A.y)
        
        # vrednosti za kretanje kroz petlju
        current_pixel   = A
        current_value   = 0
        
        # ova dva uslova omogucavaju iscrtavanje vertikalnih i horizontalnih linija
        while current_pixel.x != B.x or current_pixel.y != B.y:
            
            # racunanje vrednosti tri okolna piksela
            current_vert    = current_value + D.x
            current_diag    = current_value + D.x + D.y
            current_horz    = current_value + D.y
            
            succ = [abs(current_vert), abs(current_diag), abs(current_horz)]
            
            # trazenje minimuma torke, odnosno sledeceg piksela za iscrtavanje
            if succ.index(min(succ)) == 0:
                if (A.y <= B.y):
                    current_pixel.y += 1
                else:
                    current_pixel.y -= 1
                current_value = current_vert
            elif succ.index(min(succ)) == 1:
                if (A.y <= B.y):
                    current_pixel.y += 1
                else:
                    current_pixel.y -= 1
                current_pixel.x += 1
                current_value = current_diag
            else:
                current_pixel.x += 1
                current_value   = current_horz    
            
            self.draw_pixel(current_pixel)
            
        self.print_screen()
    
    
screen = Screen(30)
screen.draw_line(Pixel(1, 1), Pixel(25, 9))
screen.draw_line(Pixel(1, 1), Pixel(15, 26))
screen.draw_line(Pixel(3, 3), Pixel(30, 20))
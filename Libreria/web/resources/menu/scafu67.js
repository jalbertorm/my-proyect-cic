vbafu67n=xppr('afu67_0.png');vbafu67o=xppr('afu67_1.png');vbsfu67n=xppr('sfu67_0.png');vbsfu67o=xppr('sfu67_1.png');vb3fu67n=xppr('3fu67_0.png');vb3fu67o=xppr('3fu67_1.png');vbrfu67n=xppr('rfu67_0.png');vbrfu67o=xppr('rfu67_1.png');vb6fu67n=xppr('6fu67_0.png');vb6fu67o=xppr('6fu67_1.png');vb2fu67n=xppr('2fu67_0.png');vb2fu67o=xppr('2fu67_1.png');vbtfu67n=xppr('tfu67_0.png');vbtfu67o=xppr('tfu67_1.png');vbpfu67n=xppr('pfu67_0.png');vbpfu67o=xppr('pfu67_1.png');vbofu67n=xppr('ofu67_0.png');vbofu67o=xppr('ofu67_1.png');vbwfu67n=xppr('wfu67_0.png');vbwfu67o=xppr('wfu67_1.png');vb4fu67n=xppr('4fu67_0.png');vb4fu67o=xppr('4fu67_1.png');vbzfu67n=xppr('zfu67_0.png');vbzfu67o=xppr('zfu67_1.png');vbufu67n=xppr('ufu67_0.png');vbufu67o=xppr('ufu67_1.png');/*
 *==============================================================================
 *
 *     Copyright (c) 2007-2009, by Vista-buttons.com
 *     Version 2.1.2i
 *     http://vista-buttons.com
 *
 *==============================================================================
 * 
 * todo:
 * - item move into anchor
 * - item over using css
 * - item base on li structure
 *
 * variables:
 *   @btIdPref
 *   @pItem
 *   @ulIdPref
 *   @frameSubmenu
 *
 */
 
 
var btIdPref='vb';

function xppr(im) {
	var i=new Image();
	i.src=vbImgPath+'bt'+im;
	return i;
};

function xpe(id) {
	x=id.substring(0,id.length-1);
	if(document[btIdPref+x])document[btIdPref+x].src=eval(btIdPref+id+'.src');
};


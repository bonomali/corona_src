/**
 * @version $Id: PatternPdfOpenAction.java 1839 2014-04-16 02:33:51Z yukihiro-kinjyo $
 * 
 * 2012/08/22 16:48:17
 * @author yukihiro-kinjyo
 * 
 * Copyright 2011-2014 TIDAコンソーシアム  All Rights Reserved.
 */
package com.tida_okinawa.corona.help.context.action;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.tida_okinawa.corona.help.context.CoronaHelpActivator;
import com.tida_okinawa.corona.help.context.Messages;

/**
 * Coronaの構文パターンマニュアルPDFファイルを開くアクション
 * 
 * @author yukihiro-kinjo
 * 
 */
public class PatternPdfOpenAction implements IWorkbenchWindowActionDelegate {
    private IWorkbenchWindow window;


    /**
     * コンストラクター
     */
    public PatternPdfOpenAction() {
    }


    /**
     * 実行
     */
    @Override
    public void run(IAction action) {

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (desktop.isSupported(Desktop.Action.OPEN)) {
                try {
                    /* プラグインのパスから構文パターンマニュアルのパスを取得 */
                    URL entry = CoronaHelpActivator.getDefault().getBundle().getEntry("/"); //$NON-NLS-1$
                    String manualPath = FileLocator.resolve(entry).getPath();
                    manualPath = manualPath.replace("file:/", ""); //$NON-NLS-1$ //$NON-NLS-2$
                    manualPath = manualPath.replaceAll("/plugins/com\\.tida_okinawa\\.corona\\.help\\.context_.*\\.jar!", ""); //$NON-NLS-1$ //$NON-NLS-2$
                    manualPath = manualPath + "docs/Coronaマニュアル(パターン辞書).pdf"; //$NON-NLS-1$
                    /* 構文パターンマニュアルを開く */
                    File pdfFile = new File(manualPath);
                    if (!(pdfFile.exists())) {
                        MessageDialog.openError(window.getShell(), Messages.PatternPdfOpenAction_ERROR, Messages.PatternPdfOpenAction_NOT_FOUND_PATTERN_MANUAL
                                + manualPath);
                    }
                    desktop.open(pdfFile);
                } catch (IOException e) {
                    MessageDialog.openError(window.getShell(), Messages.PatternPdfOpenAction_ERROR, Messages.PatternPdfOpenAction_OPEN_FAILED_PATTERN_MANUAL);
                }
            }
        }
    }


    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    }


    @Override
    public void dispose() {
    }


    @Override
    public void init(IWorkbenchWindow window) {
        this.window = window;
    }
}
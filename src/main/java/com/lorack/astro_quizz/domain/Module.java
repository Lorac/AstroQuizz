/*******************************************************************************
 * Copyright (c) 2014
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Unknown - initial API and implementation
 *     Maxime Roussin-Bélanger - Huge refactor
 *     Simon Gamache-Poirer - Helped the huge refactor
 *     Jean Lalande - Helped on the MAC Integretion
 *******************************************************************************/

package com.lorack.astro_quizz.domain;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Module
{

    private String _name;

    private ArrayList<Question> _questions;

    private int _size;

    /**
     * @param module
     */
    public Module( String module )
    {
        _questions = new ArrayList<Question>();
        construireQuestionnaire( module );
    }

    /**
     * @param module
     */
    public void construireQuestionnaire( String module )
    {
        BufferedReader fluxEntree = null;
        String questionLabel = null;
        String[] choixReponse = new String[5];
        int nbChoix = 0;

        char reponse = 0;
        String LigneLue;
        String picturePath;

        int i = 0;
        if ( module != "" )
        {
            try
            {
                fluxEntree =
                    new BufferedReader(
                                        new InputStreamReader(
                                                               new FileInputStream( "./Ressources/" + module + ".txt" ),
                                                               "UTF-8" ) );

                do
                {

                    questionLabel = fluxEntree.readLine();
                    if ( questionLabel == null )
                    {
                        break;
                    }
                    for ( i = 0; i < 5; i++ )
                    {
                        LigneLue = fluxEntree.readLine();
                        if ( !LigneLue.isEmpty() )
                        {
                            choixReponse[i] = ( LigneLue );
                            nbChoix++;
                        }
                    }

                    try
                    {
                        reponse = fluxEntree.readLine().charAt( 0 );
                    }
                    catch ( IndexOutOfBoundsException e )
                    {
                        System.err.println( "Error: Impossible de lire la réponse de la question suivante : "
                            + questionLabel );
                        System.err.println( "Dans le module suivant : " + module );
                    }
                    picturePath = fluxEntree.readLine();

                    _questions.add( new Question( questionLabel, choixReponse, nbChoix, reponse, picturePath ) );
                    nbChoix = 0;

                }
                while ( questionLabel != "" );
                setName( module );
                setSize( _questions.size() );

            }
            catch ( IOException exc )
            {
                System.err.println( "Erreur de lecture du fichier : " + module );
            }
            catch ( NullPointerException ex )
            {
                System.err.println( "Erreur : construireQuestionnaire, dans le module suivant : " + module
                    + ", probablement des lignes de trops à la fin du fichier" );
            }

            if ( fluxEntree != null )
            {
                try
                {
                    fluxEntree.close();
                }
                catch ( IOException e )
                {
                    System.err.println( "Error can't close the ressource file" );
                }
            }

        }
    }

    /**
     * @return the _name
     */
    public String getName()
    {
        return _name;
    }

    /**
     * @return
     */
    public ArrayList<Question> getQuestions()
    {
        return _questions;
    }

    /**
     * @return the _size
     */
    public int getSize()
    {
        return _size;
    }

    /**
     * @param _name the _name to set
     */
    public void setName( String _name )
    {
        this._name = _name;
    }

    /**
     * @param _size the _size to set
     */
    public void setSize( int _size )
    {
        this._size = _size;
    }
}
